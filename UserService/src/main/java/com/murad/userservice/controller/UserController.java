package com.murad.userservice.controller;

import com.murad.userservice.dto.AuthenticationResponse;
import com.murad.userservice.dto.LoginDto;
import com.murad.userservice.dto.UserRegDto;
import com.murad.userservice.dto.UserResponse;
import com.murad.userservice.exception.UserAlreadyExistsException;
import com.murad.userservice.exception.UserNotFoundException;
import com.murad.userservice.model.AuthorityDto;
import com.murad.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserRegDto userRegDto) throws UserAlreadyExistsException,AuthenticationException {
        UserResponse userResponse = UserResponse.fromUserEntity(userService.register(userRegDto));
        LoginDto loginDto = LoginDto.builder().username(userRegDto.getUsername())
                .password(userRegDto.getPassword()).build();
        AuthenticationResponse authenticationResponse = userService.login(loginDto);
        return new ResponseEntity<>(authenticationResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginDto loginDto) throws AuthenticationException {
        AuthenticationResponse authenticationResponse = userService.login(loginDto);
        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }
    @GetMapping("/get/{name}")
    public  UserResponse getUserByName(@PathVariable String name){
        return UserResponse.fromUserEntity( userService.getUser(name));
    }

    @GetMapping("/iam")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<UserResponse> getUser() throws UserNotFoundException {

        UserResponse userResponse = UserResponse.fromUserEntity(userService.getUser());
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRegDto userRegDto)
            throws UserNotFoundException {
        UserResponse userResponse = UserResponse.fromUserEntity(userService.update(userRegDto));
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
    @PutMapping("/update/authority")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserResponse> updateAuthority(@RequestBody AuthorityDto authorityDto)
            throws UserNotFoundException {
        UserResponse userResponse = UserResponse.fromUserEntity(userService.updateAuthority(authorityDto));
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
}
