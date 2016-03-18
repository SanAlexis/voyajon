/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.services.impl;

import com.nyx.voyajon.entities.Trajet;
import com.nyx.voyajon.repositories.TrajetRepository;
import com.nyx.voyajon.services.TrajetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eisti
 */
@Service
public class TrajetServiceImpl implements TrajetService {

    @Autowired
    TrajetRepository trajetRepository;

    @Override
    @Transactional
    public Trajet save(Trajet tr) throws Exception {
        tr.setLibelle(tr.getVilleA().getLibelle() + "--" + tr.getVilleB().getLibelle());
         if(tr.getCode()==null){
            //creation du reverse trajet
            trajetRepository.save(createReverseTrajet(tr));
        }
        return trajetRepository.save(tr);
    }

    @Override
    public Trajet getReverseTrajet(Trajet t) throws Exception {
        return trajetRepository.findByVilleAAndVilleB(t.getVilleB(), t.getVilleA());
    }

    @Override
    public Trajet createReverseTrajet(Trajet tr) throws Exception {
        Trajet tr1 = new Trajet();
        tr1.setVilleA(tr.getVilleB());
        tr1.setVilleB(tr.getVilleA());
        tr1.setDuree(tr.getDuree());
        tr1.setLibelle(tr1.getVilleA().getLibelle() + "--" + tr1.getVilleB().getLibelle());
        return (tr1);
    }
}
