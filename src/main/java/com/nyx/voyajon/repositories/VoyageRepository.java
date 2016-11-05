/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.repositories;

import com.nyx.voyajon.entities.Agence;
import com.nyx.voyajon.entities.Compagnie;
import com.nyx.voyajon.entities.Trajet;
import com.nyx.voyajon.entities.Voyage;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostFilter;

/**
 *
 * @author eisti
 */
public interface VoyageRepository extends JpaRepository<Voyage, Integer> {

   
   public List<Voyage> findByDateDepart(LocalDate date_depart);

    @Query("select v from Voyage v where v.dateDepart>=:debut   and v.dateDepart<=:fin")
    @PostFilter("filterObject.agence.compagnie.code==principal.profil.code or hasRole('ADMIN') ")
    public List<Voyage> findVoyageCalendar(@Param("debut") LocalDate date_debut, @Param("fin") LocalDate date_fin);

    public List<Voyage> findByDateDepartAndTrajet(LocalDate date_depart, Trajet t);

    public List<Voyage> findByDateDepartAndTrajetAndAgence_Compagnie(LocalDate date_depart, Trajet t, Compagnie c);

    public List<Voyage> findByDateDepartAndTrajetAndAgence(LocalDate date_depart, Trajet t, Agence a);

    public List<Voyage> findByDateDepartAndAgence(LocalDate date_depart, Agence a);
}
