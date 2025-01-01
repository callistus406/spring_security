package com.callistus.spring_security.config;

import java.io.IOException;

import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.callistus.spring_security.service.CustomUserDetailsService;
import com.callistus.spring_security.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private JwtService jwtService;
    private CustomUserDetailsService customUserDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService,
            CustomUserDetailsService customUserDetailsService) {
        this.jwtService = jwtService;

        this.customUserDetailsService = customUserDetailsService;

    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        // extract token from header using the authorization header parameter

        final String authHeader = request.getHeader("Authorization");
        // check is authorization is null and if it starts with bearer

        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            // extract the actual token
            // get token
            final String jwtToken = authHeader.substring(7);
            final String userEmail = jwtService.extractUsername(jwtToken);
            // What it does: Fetches the current Authentication object from the
            // SecurityContext.
            // Purpose:
            // To check if a user is already authenticated in the current request.
            // If this value is null, it means no authentication has been set yet for the
            // current request.
            Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();

            if (userEmail != null && authentication != null) {
                // What it does: Calls the UserDetailsService to load a UserDetails object by
                // the given userEmail.
                // Why it's needed: The UserDetails object contains:
                // Information about the user (e.g., username, password, roles).
                // Spring Security requires this object to verify token validity and to create
                // an Authentication object.
                UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(userEmail);
                // validate jwt

                if (jwtService.isTokenValid(jwtToken, userDetails)) {
                    // What it does: Creates a UsernamePasswordAuthenticationToken object.
                    // Parameters:
                    // userDetails: The authenticated user's details.
                    // null: The credentials are not required here (since the JWT already serves as
                    // proof of authentication).
                    // userDetails.getAuthorities(): The roles or authorities assigned to the user,
                    // used for authorization.
                    // Purpose: This object represents an authenticated user in Spring Security.

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

                    // What it does: Adds additional details (e.g., IP address, session ID) to the
                    // authToken using the current HTTP request.
                    // Purpose: Provides more context about the authentication request, which can be
                    // useful for logging or auditing.

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // What it does: Stores the newly created authToken (authenticated user) into
                    // the SecurityContextHolder.
                    // Purpose:
                    // Makes the user's authentication available globally for the current request.
                    // Allows other parts of the application (e.g., security filters, controllers)
                    // to retrieve the user's authentication details.

                    SecurityContextHolder.getContext().setAuthentication(authToken);

                    filterChain.doFilter(request, response);
                }

            }

        } catch (Exception e) {
            // TODO: handle exception
            throw new Error("thou shall not pass");

        }
    }

}
