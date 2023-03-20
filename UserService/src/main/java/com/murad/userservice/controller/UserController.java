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
    public ResponseEntity<UserResponse> register(@RequestBody UserRegDto userRegDto) throws UserAlreadyExistsException {
        UserResponse userResponse = UserResponse.fromUserEntity(userService.register(userRegDto));
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginDto loginDto) throws AuthenticationException {
        AuthenticationResponse authenticationResponse = userService.login(loginDto);
        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<UserResponse> getUser(@PathVariable String username) throws UserNotFoundException {
        UserResponse userResponse = UserResponse.fromUserEntity(userService.getUser(username));
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
