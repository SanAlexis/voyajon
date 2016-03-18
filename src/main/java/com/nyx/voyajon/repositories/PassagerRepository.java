/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.repositories;

import com.nyx.voyajon.entities.Passager;
import com.nyx.voyajon.entities.PassagerStatut;
import com.nyx.voyajon.entities.Voyage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author eisti
 */
public interface PassagerRepository extends JpaRepository<Passager, Integer> {
    
    public List<Passager> findByStatut(PassagerStatut statut);
    
    public List<Passager> findByReservationVoyage(Voyage v);
}
