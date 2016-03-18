/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.repositories;

import com.nyx.voyajon.entities.Reservation;
import com.nyx.voyajon.entities.PassagerStatut;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author eisti
 */
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

  //  public List<Reservation> findByStatut(PassagerStatut status);

    public Reservation findByCode(Integer code);

}
