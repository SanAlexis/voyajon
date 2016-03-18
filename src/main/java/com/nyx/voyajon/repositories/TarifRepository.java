/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.repositories;

import com.nyx.voyajon.entities.Compagnie;
import com.nyx.voyajon.entities.Tarif;
import com.nyx.voyajon.entities.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author eisti
 */
public interface TarifRepository  extends JpaRepository<Tarif, Integer>{
 
    
    
   public Tarif findByTrajetAndCompagnie(Trajet t,Compagnie c);
  
}
