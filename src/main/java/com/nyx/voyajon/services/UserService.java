/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.services;

import com.nyx.voyajon.entities.security.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author eisti
 */
public interface UserService extends UserDetailsService {


    public void resetFailAttempts(String username) throws Exception;

    public void updateFailAttempts(String username) throws Exception;

    public boolean isAdmin(User currentUser)throws Exception;

    public void changeUserPassword(User principal, String newpassword, int nbremois) throws Exception;

}
