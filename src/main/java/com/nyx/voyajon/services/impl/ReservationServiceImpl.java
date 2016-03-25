/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.services.impl;

import com.nyx.voyajon.exception.BusinessException;
import com.nyx.voyajon.entities.Passager;
import com.nyx.voyajon.entities.Reservation;
import com.nyx.voyajon.entities.PassagerStatut;
import static com.nyx.voyajon.entities.PassagerStatut.ANNULE;
import static com.nyx.voyajon.entities.PassagerStatut.CONFIRME;
import com.nyx.voyajon.entities.ReservationMode;
import com.nyx.voyajon.entities.Voyage;
import com.nyx.voyajon.repositories.PassagerRepository;
import com.nyx.voyajon.repositories.ReservationRepository;
import com.nyx.voyajon.services.MailService;
import com.nyx.voyajon.services.ReservationService;
import com.nyx.voyajon.services.VoyageService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eisti
 */
@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    PassagerRepository passagerRepository;
    @Autowired
    MailService mailservice;
    @Autowired
    VoyageService voyageService;

    @Override
    public Reservation prendreReservation(Voyage v, List<Passager> passagers) throws Exception {
        if(!voyageService.checkReservationDisponible(v,Byte.valueOf( passagers.size()+""))) throw new BusinessException("Reservation Impossible ");
       return completeReservation(v, passagers, PassagerStatut.RESERVE, ReservationMode.MOBILE);
    }

    @Override
    public Reservation acheterTicket(Voyage v, List<Passager> passagers) throws Exception {
         return completeReservation(v, passagers, PassagerStatut.CONFIRME, ReservationMode.WEB);
    }

    @Override
    @Transactional
    public Reservation annulerReservation(Reservation v) throws Exception {
        v.setEnabled(false);
        List<Passager> passagers = v.getPassagers();
        passagers.forEach((p) -> {
            p.setStatut(ANNULE);
//            mailservice.sendEmail(null, new String {p.getEmail()}, null, null, null);
        });
        reservationRepository.save(v);
        passagerRepository.save(passagers);

        return v;
    }

    @Override
    //    @Scheduled
    public void annulerAnciennesReservations() throws Exception {
        //recherche toutes les resa en cours et les annule si le temps du voyage est dans 30 min
        //recherche tous les passagers dont le siege est reserve et temps du voyage est dans 30min
        List<Passager> passagers = passagerRepository.findByStatut(PassagerStatut.RESERVE);
        LocalDateTime now = LocalDateTime.now();
        for (Passager p : passagers) {
            if (p.getReservation().getVoyage().getHeureDepart().atDate(p.getReservation().getVoyage().getDateDepart()).plusMinutes(30).isAfter(now)) {
                annulerReservation(p.getReservation());
            }
        }

    }

    public Reservation completeReservation(Voyage voyage, List<Passager> passagers, PassagerStatut statut, ReservationMode mode) throws Exception {
        Reservation r = new Reservation();
        String id = null;
        while (true) {
            id = RandomStringUtils.random(8, false, true);
            if (!reservationRepository.exists(Integer.parseInt(id))) {
                break;
            }
        }
        r.setCode(Integer.parseInt(id));
        passagers.stream().forEach(((p)->{ p.setStatut(statut);p.setReservation(r);}));
        r.setPassagers(passagers);
        r.setEnabled(true);
        r.setMode(mode);
        r.setVoyage(voyage);
        r.setDateReservation(LocalDate.now());
        r.setHeureReservation(LocalTime.now());
        return reservationRepository.save(r);
    }

    @Override
    public void confirmerPassager(List<Passager> passagers) throws Exception {
        passagers.stream().forEach(((p)->{ p.setStatut(CONFIRME);}));
        passagerRepository.save(passagers);
    }
    
   
   

}
