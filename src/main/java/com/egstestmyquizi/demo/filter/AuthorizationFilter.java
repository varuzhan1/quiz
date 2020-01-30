package com.egstestmyquizi.demo.filter;


import com.egstestmyquizi.demo.model.persistence.User;
import com.egstestmyquizi.demo.service.api.UserService;
import com.egstestmyquizi.demo.util.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthorizationFilter extends AbstractAuthenticationProcessingFilter {

    private static final String HEADER_SECURITY_TOKEN = "token";
    private List<RequestMatcher> requestMatchers = new ArrayList<>();
    @Autowired
    private UserService userService;

    @Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        return requestMatchers.stream().anyMatch(item -> item.matches(request));
    }

    public AuthorizationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
        requestMatchers.add(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse)
            throws AuthenticationException {
        String token = httpServletRequest.getHeader(HEADER_SECURITY_TOKEN);
        checkToken(token);
        Authentication userAuthenticationToken = parseToken(token);
        if (userAuthenticationToken == null) {
            throw new AuthenticationServiceException("Unauthorized");

        }
        SecurityContextHolder.getContext().setAuthentication(userAuthenticationToken);
        return getAuthenticationManager().authenticate(userAuthenticationToken);
    }

    public void addRequestMatcher(RequestMatcher requestMatcher) {
        requestMatchers.add(requestMatcher);
    }

    private void checkToken(String token) {
        if (token == null) {
            throw new AuthenticationServiceException("Unauthorized");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        chain.doFilter(request, response);
    }

    private Authentication parseToken(String tokenString) {
        String[] tokenParsValues = Encrypt.decodeAccount(tokenString);
        if (tokenParsValues == null) {
            throw new AuthenticationServiceException(" Unauthorized");
        }
        User user = userService.findByUserName(tokenParsValues[0]);
        return new UsernamePasswordAuthenticationToken(tokenParsValues[0], tokenParsValues[1],
                getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return authorities;
    }
}
