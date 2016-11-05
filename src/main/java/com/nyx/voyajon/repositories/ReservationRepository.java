/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.repositories;

import com.nyx.voyajon.entities.Reservation;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;

/**
 *
 * @author eisti
 */
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

  //  public List<Reservation> findByStatut(PassagerStatut status);

    @PostFilter("filterObject.voyage.agence.compagnie.code==principal.profil.code or hasRole('ADMIN') ")
    public List<Reservation> findByDateReservation(LocalDate dateReservation);
    
    @Override
    @PostAuthorize("returnObject.voyage.agence.compagnie.code==principal.profil.code or hasRole('ADMIN') ")
    public Reservation findOne(Integer code);

}
