/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.services;

import com.nyx.voyajon.entities.Voyage;
import com.nyx.voyajon.entities.VoyageSchedule;
import com.nyx.voyajon.web.model.SearchVoyage;
import com.nyx.voyajon.web.model.VoyageCalendar;
import com.nyx.voyajon.web.model.VoyageScheduleDTO;
import java.time.LocalDate;
import java.util.List;
import org.json.simple.JSONObject;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author eisti
 */
public interface VoyageService {

    public void saveVoyageSchedule(VoyageScheduleDTO vs) throws Exception;
    
     public List<VoyageCalendar> findVoyageCalendar( LocalDate date_debut, LocalDate date_fin);

    public VoyageScheduleDTO VoyageScheduletoDTO(VoyageSchedule vs) throws Exception;

    public JSONObject searchVoyageClient(SearchVoyage sv) throws Exception;

    public boolean checkReservationDisponible(Voyage v, Byte nbre_places) throws Exception;
}
