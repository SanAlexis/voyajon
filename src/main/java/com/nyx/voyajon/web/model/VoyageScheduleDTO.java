/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.web.model;

import com.nyx.voyajon.entities.Agence;
import com.nyx.voyajon.entities.Trajet;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eisti
 */
public class VoyageScheduleDTO implements Serializable {

    private Trajet trajet;
    private Agence agence;
    private LocalDate date_debut;
    private LocalDate date_fin;
    private boolean vip;
    private boolean classic;
    private List<LocalTime> horairesLundi=new ArrayList();
    private List<LocalTime> horairesMardi=new ArrayList();
    private List<LocalTime> horairesMercredi=new ArrayList();
    private List<LocalTime> horairesJeudi=new ArrayList();
    private List<LocalTime> horairesVendredi=new ArrayList();
    private List<LocalTime> horairesSamedi=new ArrayList();
    private List<LocalTime> horairesDimanche=new ArrayList();
    

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

    public List<LocalTime> getHorairesLundi() {
        return horairesLundi;
    }

    public void setHorairesLundi(List<LocalTime> horairesLundi) {
        this.horairesLundi = horairesLundi;
    }

    public List<LocalTime> getHorairesMardi() {
        return horairesMardi;
    }

    public void setHorairesMardi(List<LocalTime> horairesMardi) {
        this.horairesMardi = horairesMardi;
    }

    public List<LocalTime> getHorairesMercredi() {
        return horairesMercredi;
    }

    public void setHorairesMercredi(List<LocalTime> horairesMercredi) {
        this.horairesMercredi = horairesMercredi;
    }

    public List<LocalTime> getHorairesJeudi() {
        return horairesJeudi;
    }

    public void setHorairesJeudi(List<LocalTime> horairesJeudi) {
        this.horairesJeudi = horairesJeudi;
    }

    public List<LocalTime> getHorairesVendredi() {
        return horairesVendredi;
    }

    public void setHorairesVendredi(List<LocalTime> horairesVendredi) {
        this.horairesVendredi = horairesVendredi;
    }

    public List<LocalTime> getHorairesSamedi() {
        return horairesSamedi;
    }

    public void setHorairesSamedi(List<LocalTime> horairesSamedi) {
        this.horairesSamedi = horairesSamedi;
    }

    public List<LocalTime> getHorairesDimanche() {
        return horairesDimanche;
    }

    public void setHorairesDimanche(List<LocalTime> horairesDimanche) {
        this.horairesDimanche = horairesDimanche;
    }
    
    

}
