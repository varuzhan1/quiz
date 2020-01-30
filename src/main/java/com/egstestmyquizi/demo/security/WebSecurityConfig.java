package com.egstestmyquizi.demo.security;

import com.egstestmyquizi.demo.filter.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailServiceImpl")
    private UserDetailsService userDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.userDetailsService(userDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().anyRequest().permitAll();
        http.addFilterBefore(restAuthenticationFilter(), AnonymousAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Bean
    AuthorizationFilter restAuthenticationFilter() throws Exception {
        AuthorizationFilter filter = new AuthorizationFilter(new AntPathRequestMatcher("/users", "GET"));
        filter.addRequestMatcher(new AntPathRequestMatcher("/question/add", "POST"));
        filter.addRequestMatcher(new AntPathRequestMatcher("/question/update", "POST"));
        filter.addRequestMatcher(new AntPathRequestMatcher("question/delete", "DELETE"));
        filter.addRequestMatcher(new AntPathRequestMatcher("/user/delete/{id}", "DELETE"));
        filter.addRequestMatcher(new AntPathRequestMatcher("/answer/add", "PUT"));
        filter.addRequestMatcher(new AntPathRequestMatcher("/users", "GET"));
        filter.addRequestMatcher(new AntPathRequestMatcher("/user/update/name", "PUT"));
        filter.addRequestMatcher(new AntPathRequestMatcher("/user/update/email", "PUT"));
        filter.addRequestMatcher(new AntPathRequestMatcher("/user/update/surname", "PUT"));
        filter.addRequestMatcher(new AntPathRequestMatcher("/user/update/password", "PUT"));
        filter.addRequestMatcher(new AntPathRequestMatcher("/user/update/enable", "PUT"));
        filter.addRequestMatcher(new AntPathRequestMatcher("/user/{id}", "GET"));
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

}
