/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.repositories;

import com.nyx.voyajon.entities.security.Profil;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PostFilter;

/**
 *
 * @author eisti
 */
public interface ProfilRepository  extends JpaRepository<Profil, Integer>{
 
    
    @Override
    @Query(value = "(select * from Profil)",nativeQuery = true)
    @PostFilter("filterObject.code==principal.profil.code or hasRole('ADMIN') ")
    public List<Profil>  findAll();
    
  
}
