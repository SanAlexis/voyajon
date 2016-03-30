/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.services;

import com.nyx.voyajon.entities.Passager;
import com.nyx.voyajon.entities.Reservation;
import com.nyx.voyajon.entities.Voyage;
import java.util.List;

/**
 *
 * @author eisti
 */
public interface ReservationService {

    public Reservation prendreReservation(Voyage v, List<Passager> passagers) throws Exception;

    public Reservation acheterTicket(Voyage v, List<Passager> passagers) throws Exception;

    public Reservation annulerReservation(Reservation v) throws Exception;

    public Reservation confirmerReservation(Reservation v) throws Exception;

    public void confirmerPassager(List<Passager> passagers) throws Exception;

    public void annulerAnciennesReservations() throws Exception;

}
