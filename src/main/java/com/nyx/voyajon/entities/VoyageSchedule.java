/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nyx.voyajon.model.superclass.SimpleAuditEntity;
import java.time.LocalDate;
import java.util.ArrayList;
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
    @ManyToOne
    @NotNull
    private Agence  agence;
    
    private boolean  vip;
    private boolean  classic;
    
    
    @NotNull
    private LocalDate  date_debut;
    @NotNull
    private LocalDate  date_fin;
    
    private String horairesLundi;
    private String horairesMardi;
    private String horairesMercredi;
    private String horairesJeudi;
    private String horairesVendredi;
    private String horairesSamedi;
    private String horairesDimanche;
    
    
    @OneToMany(mappedBy = "voyageSchedule",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Voyage> voyages=new ArrayList();


    public Trajet getTrajet() {
        return trajet;
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public boolean isClassic() {
        return classic;
    }

    public void setClassic(boolean classic) {
        this.classic = classic;
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

    public List<Voyage> getVoyages() {
        return voyages;
    }

    public void setVoyages(List<Voyage> voyages) {
        this.voyages = voyages;
    }

    public String getHorairesLundi() {
        return horairesLundi;
    }

    public void setHorairesLundi(String horairesLundi) {
        this.horairesLundi = horairesLundi;
    }

    public String getHorairesMardi() {
        return horairesMardi;
    }

    public void setHorairesMardi(String horairesMardi) {
        this.horairesMardi = horairesMardi;
    }

    public String getHorairesMercredi() {
        return horairesMercredi;
    }

    public void setHorairesMercredi(String horairesMercredi) {
        this.horairesMercredi = horairesMercredi;
    }

    public String getHorairesJeudi() {
        return horairesJeudi;
    }

    public void setHorairesJeudi(String horairesJeudi) {
        this.horairesJeudi = horairesJeudi;
    }

    public String getHorairesVendredi() {
        return horairesVendredi;
    }

    public void setHorairesVendredi(String horairesVendredi) {
        this.horairesVendredi = horairesVendredi;
    }

    public String getHorairesSamedi() {
        return horairesSamedi;
    }

    public void setHorairesSamedi(String horairesSamedi) {
        this.horairesSamedi = horairesSamedi;
    }

    public String getHorairesDimanche() {
        return horairesDimanche;
    }

    public void setHorairesDimanche(String horairesDimanche) {
        this.horairesDimanche = horairesDimanche;
    }

   
  
    
    
}
