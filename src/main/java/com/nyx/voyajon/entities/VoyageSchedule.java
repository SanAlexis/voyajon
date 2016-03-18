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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author eisti
 */
@Entity
public class VoyageSchedule   extends SimpleAuditEntity{
   
   
    @ManyToOne
    @NotNull
    private Trajet  trajet;
    @NotNull
    private LocalTime heure_depart;
    @NotNull
    private LocalDate  date_debut;
    @NotNull
    private LocalDate  date_fin;
    @ManyToOne
    @NotNull
    private Agence  agence;
    
    private boolean  frequent_lundi;
    private boolean  frequent_mardi;
    private boolean  frequent_mercredi;
    private boolean  frequent_jeudi;
    private boolean  frequent_vendredi;
    private boolean  frequent_samedi;
    private boolean  frequent_dimanche;
    
    
    @OneToMany(mappedBy = "voyageSchedule",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Voyage> voyages;


    public Trajet getTrajet() {
        return trajet;
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }

    

    

    public LocalTime getHeure_depart() {
        return heure_depart;
    }

    public void setHeure_depart(LocalTime heure_depart) {
        this.heure_depart = heure_depart;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    public boolean isFrequent_lundi() {
        return frequent_lundi;
    }

    public void setFrequent_lundi(boolean frequent_lundi) {
        this.frequent_lundi = frequent_lundi;
    }

    public boolean isFrequent_mardi() {
        return frequent_mardi;
    }

    public void setFrequent_mardi(boolean frequent_mardi) {
        this.frequent_mardi = frequent_mardi;
    }

    public boolean isFrequent_mercredi() {
        return frequent_mercredi;
    }

    public void setFrequent_mercredi(boolean frequent_mercredi) {
        this.frequent_mercredi = frequent_mercredi;
    }

    public boolean isFrequent_jeudi() {
        return frequent_jeudi;
    }

    public void setFrequent_jeudi(boolean frequent_jeudi) {
        this.frequent_jeudi = frequent_jeudi;
    }

    public boolean isFrequent_vendredi() {
        return frequent_vendredi;
    }

    public void setFrequent_vendredi(boolean frequent_vendredi) {
        this.frequent_vendredi = frequent_vendredi;
    }

    public boolean isFrequent_samedi() {
        return frequent_samedi;
    }

    public void setFrequent_samedi(boolean frequent_samedi) {
        this.frequent_samedi = frequent_samedi;
    }

    public boolean isFrequent_dimanche() {
        return frequent_dimanche;
    }

    public void setFrequent_dimanche(boolean frequent_dimanche) {
        this.frequent_dimanche = frequent_dimanche;
    }

   

    public List<Voyage> getVoyages() {
        return voyages;
    }

    public void setVoyages(List<Voyage> voyages) {
        this.voyages = voyages;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

   
   
    
    
}
