/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.repositories;

import com.nyx.voyajon.entities.Compagnie;
import com.nyx.voyajon.entities.Trajet;
import com.nyx.voyajon.entities.Voyage;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author eisti
 */
public interface VoyageRepository extends JpaRepository<Voyage, Integer> {

    public List<Voyage> findByDateDepart(LocalDate date_depart);

    public List<Voyage> findByDateDepartAndTrajet(LocalDate date_depart, Trajet t);

    public List<Voyage> findByDateDepartAndTrajetAndAgence_Compagnie(LocalDate date_depart, Trajet t, Compagnie c);
}
