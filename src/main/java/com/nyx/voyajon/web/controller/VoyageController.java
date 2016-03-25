/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.web.controller;

import com.nyx.voyajon.entities.Passager;
import com.nyx.voyajon.entities.Reservation;
import com.nyx.voyajon.entities.Tarif;
import com.nyx.voyajon.entities.Voyage;
import com.nyx.voyajon.entities.VoyageSchedule;
import com.nyx.voyajon.exception.BusinessException;
import com.nyx.voyajon.repositories.ReservationRepository;
import com.nyx.voyajon.repositories.TarifRepository;
import com.nyx.voyajon.repositories.VoyageRepository;
import com.nyx.voyajon.repositories.VoyageScheduleRepository;
import com.nyx.voyajon.services.ReservationService;
import com.nyx.voyajon.services.VoyageService;
import com.nyx.voyajon.web.model.SearchVoyage;
import com.nyx.voyajon.web.model.VoyageCalendar;
import com.nyx.voyajon.web.model.VoyageScheduleDTO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author eisti
 */
@RestController
@RequestMapping(value = "/app")
public class VoyageController {

    @Autowired
    VoyageService vss;
    @Autowired
    VoyageScheduleRepository voyageScheduleRepository;
    @Autowired
    ReservationService reservationservice;
    @Autowired
    VoyageRepository voyageRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    TarifRepository tarifRepository;

    @RequestMapping(value = "/Horaire", method = RequestMethod.GET)
    public ResponseEntity<List<LocalTime>> listHoraire() {
        List<LocalTime> heures = new ArrayList();
        LocalTime heuredebut = LocalTime.of(4, 0);
        while (true) {
            heures.add(heuredebut);
            heuredebut = heuredebut.plusMinutes(15);
            if (heuredebut.equals(LocalTime.of(1, 15))) {
                break;
            }
        }
        return new ResponseEntity<>(heures, HttpStatus.OK);
    }

    @RequestMapping(value = "/VoyageSchedule", method = RequestMethod.GET)
    public ResponseEntity<List<VoyageSchedule>> listVoyageSchedule() {
        return new ResponseEntity<>(voyageScheduleRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/VoyageSchedule/{id}", method = RequestMethod.GET)
    public ResponseEntity<VoyageScheduleDTO> findVoyageSchedule(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(vss.VoyageScheduletoDTO(voyageScheduleRepository.findOne(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "/VoyageSchedule", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> saveVoyageSchedule(@RequestBody VoyageScheduleDTO v) throws Exception {
        vss.saveVoyageSchedule(v);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/VoyageSchedule/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delVoyageSchedule(@PathVariable Integer id) throws Exception {
        voyageScheduleRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    ////VOYAGE
    @RequestMapping(value = "/Voyage/search", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> searchVoyageClient(@RequestBody SearchVoyage sv) throws Exception {
        JSONObject result = vss.searchVoyageClient(sv);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/Voyage", method = RequestMethod.GET)
    public ResponseEntity<List<Voyage>> listTodayVoyage() throws Exception {
        return new ResponseEntity<>(voyageRepository.findByDateDepart(LocalDate.now()), HttpStatus.OK);
    }

    @RequestMapping(value = "/VoyageCalendar", method = RequestMethod.GET)
    public ResponseEntity<List<VoyageCalendar>> listCalendarVoyage(@RequestParam("start") String debut, @RequestParam("end") String fin) throws Exception {
        LocalDate date_debut = LocalDate.parse(debut);
        LocalDate date_fin = LocalDate.parse(fin);
        return new ResponseEntity<>(vss.findVoyageCalendar(date_debut, date_fin), HttpStatus.OK);
    }

    @RequestMapping(value = "/Voyage", method = RequestMethod.POST)
    public ResponseEntity<Voyage> saveVoyage(@RequestBody Voyage v) throws Exception {
        return new ResponseEntity<>(voyageRepository.save(v), HttpStatus.OK);
    }

    ////RESERVATION
    @RequestMapping(value = "/Reservation", method = RequestMethod.GET)
    public ResponseEntity<List<Reservation>> listTodayReservation() throws Exception {
        return new ResponseEntity<>(reservationRepository.findByDateReservation(LocalDate.now()), HttpStatus.OK);
    }

    @RequestMapping(value = "/Reservation/{id}", method = RequestMethod.GET)
    public ResponseEntity<Reservation> findReservation(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(reservationRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/ReservationPassager/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Passager>> findPassagerReservation(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(reservationRepository.findOne(id).getPassagers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/TakeResa/{id}/{places}", method = RequestMethod.GET)
    public void prendreReservation(@PathVariable(value = "id") Integer idvoyage, @PathVariable(value = "places") Byte nombre_places, HttpSession session) throws Exception {
        Reservation resa_deja_faite = (Reservation) session.getAttribute("resa_faite");
        if (resa_deja_faite != null) {
            if (Objects.equals(resa_deja_faite.getVoyage().getCode(), idvoyage)) {
                throw new BusinessException(" Reservation deja faite pour ce voyage dans votre session");
            }
            if (resa_deja_faite.getVoyage().getDateDepart().isEqual(voyageRepository.findOne(idvoyage).getDateDepart())) {
                throw new BusinessException(" Reservation deja faite pour un voyage de la même date dans votre session");
            }
        }
        if (!vss.checkReservationDisponible(voyageRepository.findOne(idvoyage), nombre_places)) {
            throw new BusinessException("Plus de places disponibles pour ce voyage");
        }
    }

    @RequestMapping(value = "/TakeResa/{id}", method = RequestMethod.POST)
    public Reservation prendreReservation(@PathVariable(value = "id") Integer idvoyage, @RequestBody List<Passager> passagers, HttpSession session) throws Exception {
        Reservation r = reservationservice.prendreReservation(voyageRepository.findOne(idvoyage), passagers);
        session.setAttribute("resa_faite", r);
        return r;
    }

    public Reservation acheterTicket(@PathVariable(value = "id") Integer idvoyage, @RequestBody List<Passager> passagers) throws Exception {
        return reservationservice.acheterTicket(voyageRepository.findOne(idvoyage), passagers);
    }

    public void annulerReservation(@PathVariable(value = "id") Integer idresa) throws Exception {

    }

    public void confirmerReservation(@PathVariable(value = "id") Integer idresa) throws Exception {

    }

}