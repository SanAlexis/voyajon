/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.repositories;

import com.nyx.voyajon.entities.Trajet;
import com.nyx.voyajon.entities.Ville;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author eisti
 */
public interface TrajetRepository extends JpaRepository<Trajet, Integer> {

    public List<Trajet> findByVilleA(Ville villeA);

    public List<Trajet> findByVilleB(Ville villeB);

    public Trajet findByVilleAAndVilleB(Ville villeA, Ville villeB);

}
