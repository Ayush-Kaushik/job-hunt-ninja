package com.crm.prototype.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Notes: we want the filter to check everytime user sends the request
 */
@Component
@RequiredArgsConstructor // create a constructor with any field we declare
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /**
     * We can intercept incoming request and provide additional data to outgoing response
     * filterChain - chain of responsibility design pattern
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final JwtService jwtService = new JwtService();

        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmail;

        if(authHeader == null || authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwtToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwtToken);
    }
}
