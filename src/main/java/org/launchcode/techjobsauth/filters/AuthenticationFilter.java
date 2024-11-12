package org.launchcode.techjobsauth.filters;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticationFilter implements HandlerInterceptor {

    // Whitelist for paths that don't require authentication
    private static final String[] WHITELIST = {
            "/auth/login",
            "/auth/register",
            "/auth/logout"
    };


    // Method to check if a given request URI is in the whitelist
    private boolean isWhitelisted(String requestURI) {
        for (String path : WHITELIST) {
            if (requestURI.startsWith(path)) {
                return true;
            }
        }
        return false;
    }
}