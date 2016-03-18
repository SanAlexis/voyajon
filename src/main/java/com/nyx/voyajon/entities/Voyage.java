/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nyx.voyajon.model.superclass.SimpleAuditEntity;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Formula;

/**
 *
 * @author eisti
 */
@Entity
public class Voyage extends SimpleAuditEntity {

    @ManyToOne
    private Bus bus;
    @ManyToOne
    @NotNull
    private Trajet trajet;
    @ManyToOne
    @JsonIgnore
    private VoyageSchedule voyageSchedule;
    @ManyToOne
    @NotNull
    private Agence agence;
    @ManyToOne
    private Chauffeur chauffeur;
    @NotNull
    private LocalTime heureDepart;
    @NotNull
    private LocalDate dateDepart;
    @NotNull
    private LocalTime heureArrivee;
    @NotNull
    private LocalDate dateArrivee;

    @Enumerated(EnumType.STRING)
    private VoyageStatut statut;

    @Formula("(select count(p.code) from Passager p join Reservation r on r.code=p.reservation_code join Voyage v on v.code=r.voyage_code  where v.code=code and p.statut='CONFIRME')")
    private Byte nbre_places_confirmees;
    @Formula("(select count(p.code) from Passager p join Reservation r on r.code=p.reservation_code join Voyage v on v.code=r.voyage_code  where v.code=code and p.statut='RESERVE')")
    private Byte nbre_places_reservees;
//    @Formula("")
//    private BigDecimal prix;

   

    @OneToMany(mappedBy = "voyage")
    @JsonIgnore
    private List<Reservation> reservations;

    public VoyageStatut getStatut() {
        return statut;
    }

    public void setStatut(VoyageStatut statut) {
        this.statut = statut;
    }

    public Chauffeur getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(Chauffeur chauffeur) {
        this.chauffeur = chauffeur;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Trajet getTrajet() {
        return trajet;
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }

    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(LocalTime heureDepart) {
        this.heureDepart = heureDepart;
    }

    public LocalDate getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalDate dateDepart) {
        this.dateDepart = dateDepart;
    }

    public LocalTime getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(LocalTime heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public LocalDate getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(LocalDate dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public VoyageSchedule getVoyageSchedule() {
        return voyageSchedule;
    }

    public void setVoyageSchedule(VoyageSchedule voyageSchedule) {
        this.voyageSchedule = voyageSchedule;
    }

    public Byte getNbre_places_confirmees() {
        return nbre_places_confirmees;
    }

    public void setNbre_places_confirmees(Byte nbre_places_confirmees) {
        this.nbre_places_confirmees = nbre_places_confirmees;
    }

    public Byte getNbre_places_reservees() {
        return nbre_places_reservees;
    }

    public void setNbre_places_reservees(Byte nbre_places_reservees) {
        this.nbre_places_reservees = nbre_places_reservees;
    }
    

}
