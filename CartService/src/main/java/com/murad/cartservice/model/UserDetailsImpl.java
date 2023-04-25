package com.murad.cartservice.model;


import com.murad.cartservice.dto.JwtUserDto;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

@Data
public class UserDetailsImpl implements UserDetails {
    private  String username;
    private Authority authority;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
        GrantedAuthority grantedAuthority =new SimpleGrantedAuthority(authority.name());
        grantedAuthorities.add(grantedAuthority);
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static UserDetails jwtUserToUserDetails(JwtUserDto jwtUser){
        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setUsername(jwtUser.getUsername());
        userDetails.setAuthority(jwtUser.getAuthority());
        return userDetails;
    }
}
