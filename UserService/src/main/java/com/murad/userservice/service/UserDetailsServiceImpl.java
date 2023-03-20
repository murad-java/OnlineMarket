package com.murad.userservice.service;

import com.murad.userservice.model.Authority;
import com.murad.userservice.entity.UserEntity;
import com.murad.userservice.model.User;
import com.murad.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new User(userEntity.getId(),userEntity.getUsername(), userEntity.getPassword(), userEntity.getActive(),  getAuthorities(userEntity.getRole()));
    }
    public UserDetails loadUserByUsername(UserEntity userEntity) throws UsernameNotFoundException {

        return new User(userEntity.getId(),userEntity.getUsername(), userEntity.getPassword(), userEntity.getActive(), getAuthorities(userEntity.getRole()));
    }
    private Set<GrantedAuthority> getAuthorities(Authority role) {
        return Collections.singletonList(new SimpleGrantedAuthority( role.name())).stream().collect(Collectors.toSet());
    }
}