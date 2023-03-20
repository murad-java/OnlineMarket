package com.murad.userservice.dto;

import com.google.gson.Gson;
import com.murad.userservice.model.Authority;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Data
public class JwtUserDto {
    private static Gson gson =new Gson();

    private Long id;
    private String username;
    private Authority authority;

    public static String JwtUserDtoToJson(JwtUserDto userDetails){
        return gson.toJson(userDetails);
    }
    public static JwtUserDto jsonToJwtUserDto(String json){
        return gson.fromJson(json,  JwtUserDto.class);
    }
    public static String userDetailsToJson(Long id,UserDetails userDetails){
        JwtUserDto jwtUserDto =new JwtUserDto();
        jwtUserDto.setId(id);
        jwtUserDto.setUsername(userDetails.getUsername());
        jwtUserDto.setAuthority(userDetails.getAuthorities().stream().findFirst().get());
        return JwtUserDtoToJson(jwtUserDto);
    }

    private void setAuthority(GrantedAuthority grantedAuthority) {
        this.authority =Authority.valueOf( grantedAuthority.getAuthority());
    }
}
