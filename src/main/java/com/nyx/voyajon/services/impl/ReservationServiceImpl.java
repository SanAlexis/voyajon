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
import com.nyx.voyajon.services.notification.MailService;
import com.nyx.voyajon.services.ReservationService;
import com.nyx.voyajon.services.VoyageService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
        if (!voyageService.checkReservationDisponible(v, Byte.valueOf(passagers.size() + ""))) {
            throw new BusinessException("Places non disponibles");
        }
        Reservation r = completeReservation(v, passagers, PassagerStatut.RESERVE, ReservationMode.MOBILE);

        Map<String, Object> emailData = new HashMap<>();
        emailData.put("compagnie", r.getVoyage().getAgence().getLibelle());
        emailData.put("villeA", r.getVoyage().getTrajet().getVilleA().getLibelle());
        emailData.put("villeB", r.getVoyage().getTrajet().getVilleB().getLibelle());
        emailData.put("date", r.getVoyage().getDateDepart().toString());
        emailData.put("heure", r.getVoyage().getHeureDepart().toString());
        emailData.put("agenceVille", r.getVoyage().getAgence().getVille().getLibelle());
        emailData.put("agenceAdresse", r.getVoyage().getAgence().getAdresse()==null?"":r.getVoyage().getAgence().getAdresse());
        emailData.put("agenceTel", r.getVoyage().getAgence().getTelephone1()==null?"":r.getVoyage().getAgence().getTelephone1());
        emailData.put("agenceUrl", r.getVoyage().getAgence().getCompagnie().getSiteweb()==null?"":r.getVoyage().getAgence().getCompagnie().getSiteweb());

        List<String> listEmail = new ArrayList();
        passagers.stream().filter((p) -> (StringUtils.hasText(p.getEmail()))).forEach((p) -> {
            listEmail.add(p.getEmail());
        });

        if (!listEmail.isEmpty()) {
            String[] arrayEmail = new String[listEmail.size()];
            arrayEmail = listEmail.toArray(arrayEmail);
            mailservice.sendEmail("arthur.tchipnang@twinsol.com", arrayEmail, "Voyajon Booking Number #" + r.getCode(), emailData, "TakeResa.ftl");
        }
        return r;
    }

    @Override
    public Reservation acheterTicket(Voyage v, List<Passager> passagers) throws Exception {
        if (!voyageService.checkReservationDisponible(v, Byte.valueOf(passagers.size() + ""))) {
            throw new BusinessException("Places non disponibles");
        }
        return completeReservation(v, passagers, PassagerStatut.CONFIRME, ReservationMode.WEB);
    }

    @Override
    @Transactional
    public Reservation annulerReservation(Reservation v) throws Exception {
        v.setEnabled(false);
        List<Passager> passagers = v.getPassagers();
        passagers.forEach((p) -> {
            p.setStatut(ANNULE);

        });
        reservationRepository.save(v);
        passagerRepository.save(passagers);

        return v;
    }

    @Override
    @Transactional
    public Reservation confirmerReservation(Reservation v) throws Exception {
        v.setEnabled(true);
        List<Passager> passagers = v.getPassagers();
        passagers.forEach((p) -> {
            p.setStatut(CONFIRME);
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
        passagers.stream().forEach(((p) -> {
            p.setStatut(statut);
            p.setReservation(r);
        }));
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
        passagers.stream().forEach(((p) -> {
            p.setStatut(CONFIRME);
        }));
        passagerRepository.save(passagers);
    }

}
