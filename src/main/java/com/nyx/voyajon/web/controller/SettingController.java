/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.web.controller;

import com.nyx.voyajon.entities.Bus;
import com.nyx.voyajon.entities.Trajet;
import com.nyx.voyajon.entities.Ville;
import com.nyx.voyajon.repositories.BusRepository;
import com.nyx.voyajon.repositories.TrajetRepository;
import com.nyx.voyajon.repositories.VilleRepository;
import com.nyx.voyajon.services.TrajetService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
public class SettingController {

    @Autowired
    VilleRepository villeRepository;
    @Autowired
    BusRepository busRepository;

    ////CRUD VILLE
    @RequestMapping(value = "/Ville", method = RequestMethod.GET)
    public ResponseEntity<List<Ville>> listVille() {
        return new ResponseEntity<>(villeRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/Ville/{id}", method = RequestMethod.GET)
    public ResponseEntity<Ville> findVille(@PathVariable Integer id) {
        Ville v = villeRepository.findOne(id);
        return new ResponseEntity<>(v != null ? v : new Ville(), HttpStatus.OK);
    }

    @RequestMapping(value = "/Ville", method = RequestMethod.POST)
    public ResponseEntity<Ville> saveVille(@RequestBody Ville v) {
        return new ResponseEntity<>(villeRepository.save(v), HttpStatus.OK);
    }

    @RequestMapping(value = "/Villes", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> saveVilles(@RequestBody List<String> svilles) throws Exception{
        List<Ville> villes = new ArrayList();
        for (String s : svilles) {
            villes.add(new Ville(s));
        }
        villeRepository.save(villes);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/Ville/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delVille(@PathVariable Integer id) throws Exception {
        villeRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    ////CRUD BUS
    @RequestMapping(value = "/Bus", method = RequestMethod.GET)
    public ResponseEntity<List<Bus>> listBus() {
        return new ResponseEntity<>(busRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/Bus/{id}", method = RequestMethod.GET)
    public ResponseEntity<Bus> findBus(@PathVariable Integer id) {
        Bus v = busRepository.findOne(id);
        return new ResponseEntity<>(v != null ? v : new Bus(), HttpStatus.OK);
    }

    @RequestMapping(value = "/Bus", method = RequestMethod.POST)
    public ResponseEntity<Bus> saveBus(@RequestBody Bus v) {
        return new ResponseEntity<>(busRepository.save(v), HttpStatus.OK);
    }

    @RequestMapping(value = "/Bus/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delBus(@PathVariable Integer id) throws Exception {
        busRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Autowired
    TrajetRepository trajetRepository;
    @Autowired
    TrajetService trajetService;

    @RequestMapping(value = "/Trajet", method = RequestMethod.GET)
    public ResponseEntity<List<Trajet>> listTrajet() {
        return new ResponseEntity<>(trajetRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/Trajet", method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<Trajet> saveTrajet(@RequestBody Trajet tr) throws Exception {
        return new ResponseEntity<>(trajetService.save(tr), HttpStatus.OK);
    }

    @RequestMapping(value = "/Trajet/{id}", method = RequestMethod.GET)
    public ResponseEntity<Trajet> findTrajet(@PathVariable Integer id) {
        Trajet t = trajetRepository.findOne(id);
        return new ResponseEntity<>(t != null ? t : new Trajet(), HttpStatus.OK);
    }

    @RequestMapping(value = "/Trajet/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delTrajet(@PathVariable Integer id) throws Exception {
        trajetRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
