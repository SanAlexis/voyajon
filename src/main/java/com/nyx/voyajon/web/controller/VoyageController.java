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
import com.nyx.voyajon.repositories.TarifRepository;
import com.nyx.voyajon.repositories.VoyageRepository;
import com.nyx.voyajon.repositories.VoyageScheduleRepository;
import com.nyx.voyajon.services.ReservationService;
import com.nyx.voyajon.services.VoyageService;
import com.nyx.voyajon.web.model.SearchVoyage;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    TarifRepository tarifRepository;

    @RequestMapping(value = "/VoyageSchedule/list", method = RequestMethod.GET)
    public ResponseEntity<List<VoyageSchedule>> listVoyageSchedule() {
        return new ResponseEntity<>(voyageScheduleRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/VoyageSchedule/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<VoyageSchedule> findVoyageSchedule(@PathVariable Integer id) {
        VoyageSchedule v = voyageScheduleRepository.findOne(id);
        return new ResponseEntity<>(v != null ? v : new VoyageSchedule(), HttpStatus.OK);
    }

    @RequestMapping(value = "/VoyageSchedule/save", method = RequestMethod.POST)
    public ResponseEntity<VoyageSchedule> saveVoyageSchedule(@RequestBody VoyageSchedule v) throws Exception {
        return new ResponseEntity<>(vss.createVoyageSchedule(v), HttpStatus.OK);
    }

    @RequestMapping(value = "/VoyageSchedule/del/{id}", method = RequestMethod.GET)
    public ResponseEntity<HttpStatus> delVoyageSchedule(@PathVariable Integer id) throws Exception {
        voyageScheduleRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/Voyage/search", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> searchVoyageClient(@RequestBody SearchVoyage sv) throws Exception {
        JSONObject result = vss.searchVoyageClient(sv);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/TakeResa/{id}", method = RequestMethod.POST)
    public Reservation prendreReservation(@PathVariable(value = "id") Integer idvoyage, @RequestBody List<Passager> passagers) throws Exception {
        return reservationservice.prendreReservation(voyageRepository.findOne(idvoyage), passagers);
    }
   
//    @RequestMapping(value = "/TakeResa/{id}/{seats}", method = RequestMethod.GET)
//    public Reservation prendreReservation(@PathVariable(value = "id") Integer idvoyage, @PathVariable(value = "seats") Byte nbre_passagers) throws Exception {
//        return reservationservice.prendreReservation(voyageRepository.findOne(idvoyage), passagers);
//    }

    public Reservation acheterTicket(@PathVariable(value = "id") Integer idvoyage, @RequestBody List<Passager> passagers) throws Exception {
        return reservationservice.acheterTicket(voyageRepository.findOne(idvoyage), passagers);
    }

    public void annulerReservation(@PathVariable(value = "id") Integer idresa) throws Exception {

    }

    public void confirmerReservation(@PathVariable(value = "id") Integer idresa) throws Exception {

    }

}
