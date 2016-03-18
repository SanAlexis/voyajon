/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.web.controller;

import com.nyx.voyajon.entities.Bus;
import com.nyx.voyajon.entities.Ville;
import com.nyx.voyajon.repositories.BusRepository;
import com.nyx.voyajon.repositories.VilleRepository;
import java.util.List;
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
public class SettingController {

    @Autowired
    VilleRepository villeRepository;
    @Autowired
    BusRepository busRepository;

    ////CRUD VILLE
    @RequestMapping(value = "/Ville/list", method = RequestMethod.GET)
    public ResponseEntity<List<Ville>> listVille() {
        return new ResponseEntity<>(villeRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/Ville/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Ville> findVille(@PathVariable Integer id) {
        Ville v = villeRepository.findOne(id);
        return new ResponseEntity<>(v != null ? v : new Ville(), HttpStatus.OK);
    }

    @RequestMapping(value = "/Ville/save", method = RequestMethod.POST)
    public ResponseEntity<Ville> saveVille(@RequestBody Ville v) {
        return new ResponseEntity<>(villeRepository.save(v), HttpStatus.OK);
    }

    @RequestMapping(value = "/Ville/del/{id}", method = RequestMethod.GET)
    public ResponseEntity<HttpStatus> delVille(@PathVariable Integer id) throws Exception {
        villeRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    ////CRUD BUS

    @RequestMapping(value = "/Bus/list", method = RequestMethod.GET)
    public ResponseEntity<List<Bus>> listBus() {
        return new ResponseEntity<>(busRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/Bus/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Bus> findBus(@PathVariable Integer id) {
        Bus v = busRepository.findOne(id);
        return new ResponseEntity<>(v != null ? v : new Bus(), HttpStatus.OK);
    }

    @RequestMapping(value = "/Bus/save", method = RequestMethod.POST)
    public ResponseEntity<Bus> saveBus(@RequestBody Bus v) {
        return new ResponseEntity<>(busRepository.save(v), HttpStatus.OK);
    }

    @RequestMapping(value = "/Bus/del/{id}", method = RequestMethod.GET)
    public ResponseEntity<HttpStatus> delBus(@PathVariable Integer id) throws Exception {
        busRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
