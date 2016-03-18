package com.nyx.voyajon.utils;

import com.nyx.voyajon.entities.security.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public final class SecurityUtils {

    public static String getCurrentUserName() {
        User u = getCurrentUser();
        return u != null ? u.getUsername() : "";
    }

    public static User getCurrentUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User springSecurityUser = null;
            if (authentication.getPrincipal() instanceof UserDetails) {
                springSecurityUser = (User) authentication.getPrincipal();
            }
            return springSecurityUser;
        } catch (Exception e) {
            return null;
        }
    }

}
