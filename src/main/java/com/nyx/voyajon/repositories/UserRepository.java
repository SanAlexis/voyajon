/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.repositories;

import com.nyx.voyajon.entities.security.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostFilter;

/**
 *
 * @author eisti
 */
public interface UserRepository  extends JpaRepository<User, Integer>{
 
    
    public User findByUsername(String username);
    
    @PostFilter("filterObject.profil.code==principal.profil.code or hasRole('ADMIN') ")
    @Override
    public List<User> findAll();
}
