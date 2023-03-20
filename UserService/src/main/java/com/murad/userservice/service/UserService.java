package com.murad.userservice.service;

import com.murad.userservice.dto.AuthenticationResponse;
import com.murad.userservice.dto.LoginDto;
import com.murad.userservice.dto.UserRegDto;
import com.murad.userservice.dto.UserResponse;
import com.murad.userservice.model.Authority;
import com.murad.userservice.entity.UserEntity;
import com.murad.userservice.exception.UserAlreadyExistsException;
import com.murad.userservice.exception.UserNotFoundException;
import com.murad.userservice.model.AuthorityDto;
import com.murad.userservice.repository.UserRepository;
import com.murad.userservice.security.JwtUtils;
import com.murad.userservice.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;



    public UserEntity register(UserRegDto userRegDto) throws UserAlreadyExistsException {
        if (userRepository.existsByUsername(userRegDto.getUsername())) {
            throw new UserAlreadyExistsException("User with username " + userRegDto.getUsername() + " already exists");
        }

        UserEntity user = new UserEntity();
        user.setUsername(userRegDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRegDto.getPassword()));
        user.setEmail(userRegDto.getEmail());
        user.setAddress(userRegDto.getAddress());
        user.setPhone(userRegDto.getPhone());
        user.setPhotoUrl(userRegDto.getPhotoUrl());
        user.setRole(Authority.USER);

        return userRepository.save(user);
    }

    public AuthenticationResponse login(LoginDto loginDto) throws AuthenticationException {
        UserEntity userEntity = getUser(loginDto.getUsername());
        UserDetails user = userDetailsService.loadUserByUsername(userEntity);

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        String token = jwtUtils.generateToken(userEntity.getId(),user);

        return new AuthenticationResponse(token, UserResponse.fromUserEntity(userEntity));
    }

    @Transactional
    public UserEntity update(UserRegDto userRegDto) throws UserNotFoundException {
        String name= SecurityUtils.getCurrentUsername().get();
        UserEntity user = getUser(name);

        user.setEmail(userRegDto.getEmail());
        user.setAddress(userRegDto.getAddress());
        user.setPassword(passwordEncoder.encode(userRegDto.getPassword()));
        user.setPhone(userRegDto.getPhone());
        user.setPhotoUrl(userRegDto.getPhotoUrl());

        return user;
    }
    @Transactional
    public UserEntity updateAuthority(AuthorityDto authorityDto) throws UserNotFoundException {
        UserEntity user = getUser(authorityDto.getUserName());

        user.setRole(authorityDto.getAuthority());
        return user;
    }
    public UserEntity getUser(String username) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() ->
                new UserNotFoundException("User with username " + username + " not found"));
        return user;
    }
}
