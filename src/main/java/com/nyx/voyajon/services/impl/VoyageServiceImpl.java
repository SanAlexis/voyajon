/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.services.impl;

import com.nyx.voyajon.entities.Trajet;
import com.nyx.voyajon.entities.Voyage;
import com.nyx.voyajon.entities.VoyageSchedule;
import com.nyx.voyajon.entities.VoyageStatut;
import com.nyx.voyajon.repositories.VoyageRepository;
import com.nyx.voyajon.repositories.VoyageScheduleRepository;
import com.nyx.voyajon.services.TrajetService;
import com.nyx.voyajon.services.VoyageService;
import com.nyx.voyajon.web.model.SearchVoyage;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author eisti
 */
@Service
public class VoyageServiceImpl implements VoyageService {

    @Autowired
    VoyageScheduleRepository voyageScheduleRepository;
    @Autowired
    VoyageRepository voyageRepository;
    @Autowired
    TrajetService trajetService;

    @Override
    public VoyageSchedule createVoyageSchedule(VoyageSchedule vs) throws Exception {
        List<Voyage> voyages = new ArrayList();
        if (vs.isFrequent_lundi()) {
            createVoyage(voyages, vs, 1);
        }
        if (vs.isFrequent_mardi()) {
            createVoyage(voyages, vs, 2);
        }
        if (vs.isFrequent_mercredi()) {
            createVoyage(voyages, vs, 3);
        }
        if (vs.isFrequent_jeudi()) {
            createVoyage(voyages, vs, 4);
        }
        if (vs.isFrequent_vendredi()) {
            createVoyage(voyages, vs, 5);
        }
        if (vs.isFrequent_samedi()) {
            createVoyage(voyages, vs, 6);
        }
        if (vs.isFrequent_dimanche()) {
            createVoyage(voyages, vs, 7);
        }

        vs.setVoyages(voyages);
        return voyageScheduleRepository.save(vs);
    }

    public void createVoyage(List<Voyage> voyages, VoyageSchedule vs, int fromDay) {
        LocalDate d1 = nextDayFromDate(vs.getDate_debut(), fromDay);
        while (d1.isBefore(vs.getDate_fin())) {
            Voyage v = new Voyage();
            v.setStatut(VoyageStatut.PROGRAMME);
            v.setTrajet(vs.getTrajet());
            v.setHeureDepart(vs.getHeure_depart());
            v.setDateDepart(d1);
            LocalDateTime arrivee = LocalDateTime.of(d1, vs.getHeure_depart()).plusMinutes(vs.getTrajet().getDuree());
            v.setDateArrivee(arrivee.toLocalDate());
            v.setHeureArrivee(arrivee.toLocalTime());
            v.setAgence(vs.getAgence());
            v.setVoyageSchedule(vs);
            voyages.add(v);
            d1 = d1.plusWeeks(1);
        }
    }

    public LocalDate nextDayFromDate(LocalDate date_debut, int day) {
        TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();
        LocalDate date_result = date_debut.with(fieldISO, day);
        if (date_result.isBefore(date_debut)) {
            date_result = date_result.plusWeeks(1);
        }

        return date_result;
    }

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();
        System.out.println(now.with(fieldISO, 1)); // 2015-02-09 (Monday)

        TemporalField fieldUS = WeekFields.of(Locale.US).dayOfWeek();
        System.out.println(now.with(fieldUS, 1)); // 2015-02-08 (Sunday)

        System.out.println(now.with(DayOfWeek.FRIDAY));
    }

    @Override
    public JSONObject searchVoyageClient(SearchVoyage sv) throws Exception {
        JSONObject result = new JSONObject();
        if (sv.getCompagnie() == null) {
            if (sv.isRetour()) {
                Trajet trajetretour = trajetService.getReverseTrajet(sv.getTrajet());
                result.put("retour", filtreVoyage(voyageRepository.findByDateDepartAndTrajet(sv.getDate_retour(), trajetretour), sv.getPlaces()));
            }
            result.put("aller", filtreVoyage(voyageRepository.findByDateDepartAndTrajet(sv.getDate_depart(), sv.getTrajet()), sv.getPlaces()));
        } else {
            if (sv.isRetour()) {
                Trajet trajetretour = trajetService.getReverseTrajet(sv.getTrajet());
                result.put("retour", filtreVoyage(voyageRepository.findByDateDepartAndTrajetAndAgence_Compagnie(sv.getDate_retour(), trajetretour, sv.getCompagnie()), sv.getPlaces()));
            }
            result.put("aller", filtreVoyage(voyageRepository.findByDateDepartAndTrajetAndAgence_Compagnie(sv.getDate_depart(), sv.getTrajet(), sv.getCompagnie()), sv.getPlaces()));
        }

        return result;
    }

    public List<Voyage> filtreVoyage(List<Voyage> voyages, Byte nbre_places_a_reserver) {
        voyages.stream().filter(v -> (checkReservationDisponible(v, nbre_places_a_reserver)
                && (LocalDateTime.of(v.getDateDepart(), v.getHeureDepart()).plusMinutes(30).isAfter(LocalDateTime.now()))))
                .collect(Collectors.toList());
        return voyages;
    }

    @Override
    public boolean checkReservationDisponible(Voyage v, Byte nbre_places_a_reserver) {
        return ((v.getBus() == null ? 70 : v.getBus().getNbre_places()) >= v.getNbre_places_confirmees() + v.getNbre_places_reservees() + nbre_places_a_reserver);
    }
}
