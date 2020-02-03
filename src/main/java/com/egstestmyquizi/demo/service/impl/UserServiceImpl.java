package com.egstestmyquizi.demo.service.impl;

import com.egstestmyquizi.demo.exception.EmptyUsersException;
import com.egstestmyquizi.demo.exception.UserNotFoundException;
import com.egstestmyquizi.demo.exception.UserRegistrationException;

import com.egstestmyquizi.demo.model.dto.JwtAuthenticationRequest;
import com.egstestmyquizi.demo.model.dto.JwtAuthenticationResponse;
import com.egstestmyquizi.demo.model.dto.LeaderBoardDto;
import com.egstestmyquizi.demo.model.dto.UserDto;
import com.egstestmyquizi.demo.model.persistence.Role;
import com.egstestmyquizi.demo.model.persistence.User;
import com.egstestmyquizi.demo.repository.UserRepository;
import com.egstestmyquizi.demo.security.JwtTokenUtil;
import com.egstestmyquizi.demo.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    @Transactional
    public void save(User newUser) throws UserRegistrationException {
        User user = userRepository.findByEmail(newUser.getEmail());
        if (user == null && newUser.getPassword() != null && newUser.getName() != null && newUser.getSurName() != null) {
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            newUser.setRole(Role.valueOf("USER"));
            newUser.setPoints(0);
            userRepository.save(newUser);
        } else {
            throw new UserRegistrationException("User already created or you have missing some field");
        }
    }


    @Override
    @Transactional
    public ResponseEntity loginByEmailAndPassword(JwtAuthenticationRequest authenticationRequest) throws UserNotFoundException {

        User user = findByEmail(authenticationRequest.getEmail());
        if (passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
            String token = jwtTokenUtil.generateToken(user.getEmail());
            return ResponseEntity.ok(JwtAuthenticationResponse.builder()
                    .token(token)
                    .userDto(UserDto.builder()
                            .id(user.getId())
                            .name(user.getName())
                            .surname(user.getSurName())
                            .email(user.getEmail())
                            .role(user.getRole())
                            .build())
                    .build());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("password is not correct");
    }

    @Override
    @Transactional
    public List<UserDto> findAll() throws EmptyUsersException {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        if (users.isEmpty()) {
            throw new EmptyUsersException("There is no users!");
        } else {
            for (int i = 0; i < users.size(); i++) {
                UserDto userDto = UserDto.builder()
                        .id(users.get(i).getId())
                        .name(users.get(i).getName())
                        .surname(users.get(i).getSurName())
                        .email(users.get(i).getEmail())
                        .role(users.get(i).getRole())
                        .build();
                userDtos.add(userDto);
            }
            return userDtos;
        }
    }

    @Override
    @Transactional
    public UserDto findById(Integer id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User not found");
        } else {

                UserDto userDto = UserDto.builder()
                        .id(user.get().getId())
                        .name(user.get().getName())
                        .surname(user.get().getSurName())
                        .email(user.get().getEmail())
                        .role(user.get().getRole())
                        .build();


            return userDto;
        }

    }

    @Override
    @Transactional
    public User findByEmail(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        return user;
    }

    @Override
    @Transactional
    public void deleteById(Integer id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateEmail(String email, String newEmail) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        user.setEmail(newEmail);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateName(String email, String name) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        user.setName(name);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateSurName(String email, String surName) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        user.setSurName(surName);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updatePassword(String email, String newPassword) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }


    @Override
    public List<LeaderBoardDto> leaderBoard() {
        List<User> users = userRepository.findAllBy(Sort.by(Sort.Direction.DESC, "points"));
        List<LeaderBoardDto> leaderBoardDtos = new ArrayList<>();

        for (int i = 0; i < users.size(); i++){
            LeaderBoardDto leaderBoardDto = LeaderBoardDto.builder()
                    .name(users.get(i).getName())
                    .points(users.get(i).getPoints())
                    .build();
            leaderBoardDtos.add(leaderBoardDto);
        }
        return leaderBoardDtos;
    }


}
