/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.web.controller;

import com.nyx.voyajon.entities.Compagnie;
import com.nyx.voyajon.entities.Agence;
import com.nyx.voyajon.entities.Tarif;
import com.nyx.voyajon.entities.Trajet;
import com.nyx.voyajon.repositories.AgenceRepository;
import com.nyx.voyajon.repositories.CompagnieRepository;
import com.nyx.voyajon.repositories.TarifRepository;
import com.nyx.voyajon.repositories.VilleRepository;
import com.nyx.voyajon.services.TrajetService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
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
public class CompagnieController {

    @Autowired
    CompagnieRepository compagnieRepository;
    @Autowired
    VilleRepository villeRepository;
    @Autowired
    AgenceRepository agenceRepository;
    @Autowired
    TarifRepository tarifRepository;
    @Autowired
    TrajetService trajetService;

    @RequestMapping(value = "/Compagnie", method = RequestMethod.GET)
    public ResponseEntity<List<Compagnie>> listCompagnie() {
        return new ResponseEntity<>(compagnieRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/Compagnie", method = RequestMethod.POST)
    public ResponseEntity<Compagnie> saveCompagnie(@RequestBody Compagnie tr) {
        return new ResponseEntity<>(compagnieRepository.save(tr), HttpStatus.OK);
    }

    @RequestMapping(value = "/Compagnie/{id}", method = RequestMethod.GET)
    public ResponseEntity<Compagnie> findCompagnie(@PathVariable Integer id) {
        Compagnie c = compagnieRepository.findOne(id);
        return new ResponseEntity<>(c != null ? c : new Compagnie(), HttpStatus.OK);
    }

    @RequestMapping(value = "/Compagnie/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delCompagnie(@PathVariable Integer id) throws Exception {
        compagnieRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/Agencelist/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Agence>> listAgenceCompagnie(@PathVariable Integer id) {
        if (id == -1) {
            return new ResponseEntity<>(agenceRepository.findAll(), HttpStatus.OK);
        }
        return new ResponseEntity<>(compagnieRepository.findOne(id).getAgences(), HttpStatus.OK);
    }

    @RequestMapping(value = "/Agence/{id}", method = RequestMethod.GET)
    public ResponseEntity<Agence> findAgence(@PathVariable Integer id) {
        return new ResponseEntity<>(agenceRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/Agence/{id}", method = RequestMethod.POST)
    public ResponseEntity<Agence> addAgence(@RequestBody Agence pv, @PathVariable Integer id) {
        Compagnie tr = compagnieRepository.findOne(id);
        pv.setCompagnie(tr);
        return new ResponseEntity<>(agenceRepository.save(pv), HttpStatus.OK);
    }

    @RequestMapping(value = "/Tariflist/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Tarif>> listTarifCompagnie(@PathVariable Integer id) {
        if (id == -1) {
            return new ResponseEntity<>(tarifRepository.findAll(), HttpStatus.OK);
        }
        return new ResponseEntity<>(compagnieRepository.findOne(id).getTarifs(), HttpStatus.OK);
    }

    @RequestMapping(value = "/Tarif/{id}", method = RequestMethod.GET)
    public ResponseEntity<Tarif> findTarif(@PathVariable Integer id) {
        return new ResponseEntity<>(tarifRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/Tarif/{id}", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> addTarif(@RequestBody Tarif t, @PathVariable Integer id)  throws Exception{
        Compagnie c = compagnieRepository.findOne(id);
        t.setCompagnie(c);
        //create Tarif for reverse Trajet
        Trajet tr=  trajetService.getReverseTrajet(t.getTrajet());
        Tarif t1=tarifRepository.findByTrajetAndCompagnie(tr, c);
        if(t1==null) t1=new Tarif();
        BeanUtils.copyProperties(t, t1,"code");
        t1.setTrajet(tr);
        
        List<Tarif> tarifs=new ArrayList();
        tarifs.add(t);tarifs.add(t1);
        
        tarifRepository.save(tarifs);
        return new ResponseEntity<>( HttpStatus.OK);
    }

}
