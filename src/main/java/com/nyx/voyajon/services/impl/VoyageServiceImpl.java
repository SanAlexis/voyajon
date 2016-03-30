/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.services.impl;

import com.nyx.voyajon.entities.Tarif;
import com.nyx.voyajon.entities.Trajet;
import com.nyx.voyajon.entities.Voyage;
import com.nyx.voyajon.entities.VoyageSchedule;
import com.nyx.voyajon.entities.VoyageStatut;
import com.nyx.voyajon.exception.BusinessException;
import com.nyx.voyajon.repositories.TarifRepository;
import com.nyx.voyajon.repositories.VoyageRepository;
import com.nyx.voyajon.repositories.VoyageScheduleRepository;
import com.nyx.voyajon.services.TrajetService;
import com.nyx.voyajon.services.VoyageService;
import com.nyx.voyajon.web.model.SearchVoyage;
import com.nyx.voyajon.web.model.VoyageCalendar;
import com.nyx.voyajon.web.model.VoyageScheduleDTO;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Arrays;
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
    @Autowired
    TarifRepository tarifRepository;

    public String ListToString(List<String> list) {
        try {
            String result = "";
            result = list.stream().map((s) -> s + ",").reduce(result, String::concat);
            return result;
        } catch (Exception e) {
            return "";
        }

    }

    public List<String> StringToList(String st) {
        try {
            String[] ss = st.split(",");
            List<String> result = new ArrayList();
            result.addAll(Arrays.asList(ss));
            return result;
        } catch (Exception e) {
            return new ArrayList();
        }

    }

    public List<LocalTime> ParseTime(List<String> horairesString) throws Exception {
        List<LocalTime> times = new ArrayList();
        try{
        horairesString.stream().forEach((time) -> {
            times.add(LocalTime.parse(time, DateTimeFormatter.ISO_TIME));
        });
        }catch(DateTimeParseException dte){
         throw  new BusinessException("Mauvaise Heure "+dte.getParsedString());
        }
        return times;
    }

    public VoyageSchedule VoyageSchedulefromDTO(VoyageScheduleDTO vsdto) throws Exception {
        VoyageSchedule vs = new VoyageSchedule();
        vs.setAgence(vsdto.getAgence());
        vs.setClassic(vsdto.isClassic());
        vs.setVip(vsdto.isVip());
        vs.setDate_debut(vsdto.getDate_debut());
        vs.setDate_fin(vsdto.getDate_fin());
        vs.setTrajet(vsdto.getTrajet());

        vs.setHorairesLundi(ListToString(vsdto.getHorairesLundi()));
        vs.setHorairesMardi(ListToString(vsdto.getHorairesMardi()));
        vs.setHorairesMercredi(ListToString(vsdto.getHorairesMercredi()));
        vs.setHorairesJeudi(ListToString(vsdto.getHorairesJeudi()));
        vs.setHorairesVendredi(ListToString(vsdto.getHorairesVendredi()));
        vs.setHorairesSamedi(ListToString(vsdto.getHorairesSamedi()));
        vs.setHorairesDimanche(ListToString(vsdto.getHorairesDimanche()));

        return vs;
    }

    @Override
    public VoyageScheduleDTO VoyageScheduletoDTO(VoyageSchedule vs) throws Exception {
        VoyageScheduleDTO vsdto = new VoyageScheduleDTO();
        vsdto.setAgence(vs.getAgence());
        vsdto.setTrajet(vs.getTrajet());
        vsdto.setClassic(vs.isClassic());
        vsdto.setVip(vs.isVip());
        vsdto.setDate_debut(vs.getDate_debut());
        vsdto.setDate_fin(vs.getDate_fin());

        vsdto.setHorairesLundi(StringToList(vs.getHorairesLundi()));
        vsdto.setHorairesMardi(StringToList(vs.getHorairesMardi()));
        vsdto.setHorairesMercredi(StringToList(vs.getHorairesMercredi()));
        vsdto.setHorairesJeudi(StringToList(vs.getHorairesJeudi()));
        vsdto.setHorairesVendredi(StringToList(vs.getHorairesVendredi()));
        vsdto.setHorairesSamedi(StringToList(vs.getHorairesSamedi()));
        vsdto.setHorairesDimanche(StringToList(vs.getHorairesDimanche()));

        return vsdto;
    }

    @Override
    public void saveVoyageSchedule(VoyageScheduleDTO vs) throws Exception {

        List<Voyage> voyages = new ArrayList();
        if (!vs.getHorairesLundi().isEmpty()) {
            createVoyage(voyages, vs, DayOfWeek.MONDAY, ParseTime(vs.getHorairesLundi()));
        }
        if (!vs.getHorairesMardi().isEmpty()) {
            createVoyage(voyages, vs, DayOfWeek.TUESDAY, ParseTime(vs.getHorairesMardi()));
        }
        if (!vs.getHorairesMercredi().isEmpty()) {
            createVoyage(voyages, vs, DayOfWeek.WEDNESDAY, ParseTime(vs.getHorairesMercredi()));
        }
        if (!vs.getHorairesJeudi().isEmpty()) {
            createVoyage(voyages, vs, DayOfWeek.THURSDAY, ParseTime(vs.getHorairesJeudi()));
        }
        if (!vs.getHorairesVendredi().isEmpty()) {
            createVoyage(voyages, vs, DayOfWeek.FRIDAY, ParseTime(vs.getHorairesVendredi()));
        }
        if (!vs.getHorairesSamedi().isEmpty()) {
            createVoyage(voyages, vs, DayOfWeek.SATURDAY, ParseTime(vs.getHorairesSamedi()));
        }
        if (!vs.getHorairesDimanche().isEmpty()) {
            createVoyage(voyages, vs, DayOfWeek.SUNDAY, ParseTime(vs.getHorairesDimanche()));
        }

        VoyageSchedule vss = VoyageSchedulefromDTO(vs);
        vss.setVoyages(voyages);
        voyageScheduleRepository.save(vss);
    }

    public void createVoyage(List<Voyage> voyages, VoyageScheduleDTO vs, DayOfWeek fromDay, List<LocalTime> heures) throws Exception{
        LocalDate d1 = nextDayFromDate(vs.getDate_debut(), fromDay);
        while (d1.isBefore(vs.getDate_fin())) {
            Tarif tarif = tarifRepository.findByTrajetAndCompagnie(vs.getTrajet(), vs.getAgence().getCompagnie());
            if (vs.isVip()) {
                createVoyage(voyages, vs, d1, true, tarif == null ? BigDecimal.ZERO : tarif.getPrix_vip_aller(), heures);
            }
            createVoyage(voyages, vs, d1, false, tarif == null ? BigDecimal.ZERO : tarif.getPrix_classic_aller(), heures);
            d1 = d1.plusWeeks(1);
        }
    }

    public void createVoyage(List<Voyage> voyages, VoyageScheduleDTO vs, LocalDate dateDepart, boolean vip, BigDecimal prix, List<LocalTime> heures) throws Exception{
        for (LocalTime heureDepart : heures) {
            Voyage v = new Voyage();
            v.setStatut(VoyageStatut.PROGRAMME);
            v.setTrajet(vs.getTrajet());
            v.setHeureDepart(heureDepart);
            v.setDateDepart(dateDepart);
            LocalDateTime arrivee = LocalDateTime.of(dateDepart, heureDepart).plusMinutes(vs.getTrajet().getDuree());
            v.setDateArrivee(arrivee.toLocalDate());
            v.setHeureArrivee(arrivee.toLocalTime());
            v.setAgence(vs.getAgence());
//            v.setVoyageSchedule(vs);
            v.setVip(vip);
            v.setTarif(prix);
            voyages.add(v);
        }
    }

    public LocalDate nextDayFromDate(LocalDate date_debut, DayOfWeek j) {
        TemporalField fieldISO = WeekFields.of(Locale.FRANCE).dayOfWeek();
        LocalDate date_result = date_debut.with(fieldISO, j.getValue());
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

    @Override
    public List<VoyageCalendar> findVoyageCalendar(LocalDate date_debut, LocalDate date_fin) {
        List<Voyage> voyages = voyageRepository.findVoyageCalendar(date_debut, date_fin);
        List<VoyageCalendar> result = new ArrayList();
        voyages.stream().map((v) -> {
            VoyageCalendar vc = new VoyageCalendar();
            vc.setId(v.getCode());
            vc.setTitle(" ");
            vc.setStart(v.getDateDepart().atTime(v.getHeureDepart()).toInstant(ZoneOffset.UTC).toString());
            vc.setEnd(v.getDateDepart().atTime(v.getHeureDepart()).plusMinutes(30).toInstant(ZoneOffset.UTC).toString());
            vc.setClassName(VoyageStatut.PROGRAMME.equals(v.getStatut()) ? "b-l b-2x b-primary" : "b-l b-2x b-warning");
            vc.setUrl("#/form/Voyage/" + v.getCode());
            return vc;
        }).forEach((vc) -> {
            result.add(vc);
        });
        return result;
    }

}
