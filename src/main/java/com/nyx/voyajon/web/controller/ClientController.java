/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.web.controller;

import com.nyx.voyajon.entities.Passager;
import com.nyx.voyajon.entities.Reservation;
import com.nyx.voyajon.exception.BusinessException;
import com.nyx.voyajon.repositories.CompagnieRepository;
import com.nyx.voyajon.repositories.TrajetRepository;
import com.nyx.voyajon.repositories.VoyageRepository;
import com.nyx.voyajon.services.ReservationService;
import com.nyx.voyajon.services.VoyageService;
import com.nyx.voyajon.web.model.SearchVoyage;
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
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author eisti
 */
@RestController
@RequestMapping(value = "/app/client")
public class ClientController {

    @Autowired
    VoyageService vss;
    @Autowired
    TrajetRepository tr;
    @Autowired
    CompagnieRepository cr;
    @Autowired
    VoyageRepository vr;
    @Autowired
    ReservationService rs;

    @RequestMapping(value = "/Voyage/search", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> searchVoyageClient(@RequestBody SearchVoyage sv) throws Exception {
        JSONObject result = vss.searchVoyageClient(sv);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/Voyage/search", method = RequestMethod.GET)
    public ResponseEntity<JSONObject> searchVoyageClientModel() throws Exception {
        JSONObject result = new JSONObject();
        result.put("trajets", tr.findAll());
        result.put("compagnies", cr.findAll());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/TakeResa/{id}/{places}", method = RequestMethod.GET)
    public void prendreReservation(@PathVariable(value = "id") Integer idvoyage, @PathVariable(value = "places") Byte nombre_places, HttpSession session) throws Exception {
        Reservation resa_deja_faite = (Reservation) session.getAttribute("resa_faite");
        if (resa_deja_faite != null) {
            if (Objects.equals(resa_deja_faite.getVoyage().getCode(), idvoyage)) {
                throw new BusinessException(" Reservation deja faite pour ce voyage dans votre session");
            }
            if (resa_deja_faite.getVoyage().getDateDepart().isEqual(vr.findOne(idvoyage).getDateDepart())) {
                throw new BusinessException(" Reservation deja faite pour un voyage de la même date dans votre session");
            }
        }
        if (!vss.checkReservationDisponible(vr.findOne(idvoyage), nombre_places)) {
            throw new BusinessException("Plus de places disponibles pour ce voyage");
        }
    }

    @RequestMapping(value = "/TakeResa/{id}", method = RequestMethod.POST)
    public Reservation prendreReservation(@PathVariable(value = "id") Integer idvoyage, @RequestBody List<Passager> passagers, HttpSession session) throws Exception {
        Reservation r = rs.prendreReservation(vr.findOne(idvoyage), passagers);
        session.setAttribute("resa_faite", r);
        return r;
    }

}
