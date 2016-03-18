/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.services;

import com.nyx.voyajon.entities.Voyage;
import com.nyx.voyajon.entities.VoyageSchedule;
import com.nyx.voyajon.web.model.SearchVoyage;
import org.json.simple.JSONObject;

/**
 *
 * @author eisti
 */
public interface VoyageService {

    public VoyageSchedule createVoyageSchedule(VoyageSchedule vs) throws Exception;


    public JSONObject searchVoyageClient(SearchVoyage  sv) throws Exception;
    
    public boolean  checkReservationDisponible(Voyage v,Byte nbre_places) throws Exception;
}
