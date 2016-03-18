/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.security.http;

import com.nyx.voyajon.services.UserService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 *
 * @author tchipi
 */
@Component
public class CustomDaoAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    UserService us;

    @Override
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        super.setUserDetailsService(userDetailsService);
    }

    @Override
    protected void doAfterPropertiesSet() throws Exception {
        // Assert.notNull(this.userDetailsService, "A UserDetailsService must be set");
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        try {

            Authentication auth = super.authenticate(authentication);
            try {
                //if reach here, means login success, else an exception will be thrown
                //reset the user_attempts
                us.resetFailAttempts(authentication.getName());
            } catch (Exception ex) {
                Logger.getLogger(CustomDaoAuthenticationProvider.class.getName()).log(Level.SEVERE, null, ex);
            }

            return auth;
        } catch (BadCredentialsException e) {
            try {
                //invalid login, update to user_attempts
                us.updateFailAttempts(authentication.getName());
            } catch (Exception ex) {
                Logger.getLogger(CustomDaoAuthenticationProvider.class.getName()).log(Level.SEVERE, null, ex);
            }
            throw e;

        } catch (AuthenticationException ex) {
            throw ex;
        }
    }

}
