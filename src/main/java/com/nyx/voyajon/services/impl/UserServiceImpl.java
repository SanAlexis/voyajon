/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.services.impl;

import com.nyx.voyajon.repositories.UserRepository;
import com.nyx.voyajon.entities.security.Profil;
import com.nyx.voyajon.entities.security.User;
import com.nyx.voyajon.services.UserService;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author tchipi
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userdao;

    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User modeluser = userdao.findByUsername(username);
            if (modeluser == null) {
                throw new UsernameNotFoundException("Username or Password incorrect");
            }
            return modeluser;
        } catch (Exception ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new UsernameNotFoundException(ex.getMessage());
        }
    }

    @Override
    public void changeUserPassword(User principal, String newpassword, int nbremois) throws Exception {
        principal.setPassword(newpassword);
        principal.setDate_changementpwd(LocalDateTime.now());
        principal.setDate_expirationpwd(principal.getDate_changementpwd().plusMonths(nbremois));
        userdao.save(principal);
    }

    @Override
    public boolean isAdmin(User principal) {
        return principal.getAuthorities().stream().anyMatch((ga) -> (ga.getAuthority().equalsIgnoreCase("ROLE_ADMIN")));
    }

   

    @Override
    public void resetFailAttempts(String name) throws Exception {
        User u = (User) loadUserByUsername(name);
        u.setLogin_attempts(new Byte("0"));
        u.setLastaccessdate(LocalDateTime.now());
         userdao.save(u);
    }

    @Override
    public void updateFailAttempts(String name) throws Exception {
        User u = (User) loadUserByUsername(name);
        u.setLogin_attempts((byte) (u.getLogin_attempts() + 1));
        userdao.save(u);
    }

    

}
