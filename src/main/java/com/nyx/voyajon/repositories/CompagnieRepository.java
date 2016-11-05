/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.repositories;

import com.nyx.voyajon.entities.Compagnie;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostFilter;

/**
 *
 * @author eisti
 */
public interface CompagnieRepository  extends JpaRepository<Compagnie, Integer>{
 
    
    @PostFilter("filterObject.code==principal.profil.code or hasRole('ADMIN') ")
    @Override
    public List<Compagnie> findAll();
    
  
}
