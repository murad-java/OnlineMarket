package com.murad.operationsservice.security;

import com.murad.operationsservice.dto.JwtUserDto;
import com.murad.operationsservice.model.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {


    private final UserDetailsService jwtUserDetailsService;

    private final JwtUtils jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;
        // Получаем URL-адрес запроса
        String requestUrl = request.getRequestURI();

        // Создаем объект AntPathMatcher
        AntPathMatcher pathMatcher = new AntPathMatcher();

//         Проверяем, является ли URL-адрес разрешенным
        if (pathMatcher.match("/product/**", requestUrl)||
                pathMatcher.match("/category/**", requestUrl)||
                pathMatcher.match("/subcategories/**", requestUrl)) {
            // Если URL-адрес разрешенный, пропускаем запрос без проверки
            chain.doFilter(request, response);

        }else {
            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
                jwtToken = requestTokenHeader.substring(7);
                try {
                    username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                } catch (IllegalArgumentException e) {
                    logger.error("Unable to get JWT Token", e);
                } catch (ExpiredJwtException e) {
                    logger.error("JWT Token has expired", e);
                }
            } else {
                logger.warn("JWT Token does not begin with Bearer String");
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                JwtUserDto jwtUserDto = jwtTokenUtil.getJwtUserFromToken(jwtToken);

                if (jwtTokenUtil.validateToken(jwtToken)) {
                    UserDetails userDetails = UserDetailsImpl.jwtUserToUserDetails(jwtUserDto);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            chain.doFilter(request, response);
        }
    }
}

