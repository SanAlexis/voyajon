/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.web.controller;

import com.nyx.voyajon.entities.Tarif;
import com.nyx.voyajon.entities.Trajet;
import com.nyx.voyajon.repositories.TrajetRepository;
import com.nyx.voyajon.services.TrajetService;
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
public class TrajetController {

    @Autowired
    TrajetRepository trajetRepository;
    @Autowired
    TrajetService trajetService;
    

    @RequestMapping(value = "/Trajet/list", method = RequestMethod.GET)
    public ResponseEntity<List<Trajet>> listTrajet() {
        return new ResponseEntity<>(trajetRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/Trajet/save", method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<Trajet> saveTrajet(@RequestBody Trajet tr) throws Exception {
        return new ResponseEntity<>(trajetService.save(tr), HttpStatus.OK);
    }

    @RequestMapping(value = "/Trajet/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Trajet> findTrajet(@PathVariable Integer id) {
        Trajet t = trajetRepository.findOne(id);
        return new ResponseEntity<>(t != null ? t : new Trajet(), HttpStatus.OK);
    }
    
     @RequestMapping(value = "/Trajet/del/{id}", method = RequestMethod.GET)
    public ResponseEntity<HttpStatus> delTrajet(@PathVariable Integer id) throws Exception {
        trajetRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    

   
}
