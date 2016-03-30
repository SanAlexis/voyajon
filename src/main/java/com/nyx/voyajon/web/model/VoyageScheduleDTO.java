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
    private List<String> horairesLundi=new ArrayList();
    private List<String> horairesMardi=new ArrayList();
    private List<String> horairesMercredi=new ArrayList();
    private List<String> horairesJeudi=new ArrayList();
    private List<String> horairesVendredi=new ArrayList();
    private List<String> horairesSamedi=new ArrayList();
    private List<String> horairesDimanche=new ArrayList();
    

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

    public List<String> getHorairesLundi() {
        return horairesLundi;
    }

    public void setHorairesLundi(List<String> horairesLundi) {
        this.horairesLundi = horairesLundi;
    }

    public List<String> getHorairesMardi() {
        return horairesMardi;
    }

    public void setHorairesMardi(List<String> horairesMardi) {
        this.horairesMardi = horairesMardi;
    }

    public List<String> getHorairesMercredi() {
        return horairesMercredi;
    }

    public void setHorairesMercredi(List<String> horairesMercredi) {
        this.horairesMercredi = horairesMercredi;
    }

    public List<String> getHorairesJeudi() {
        return horairesJeudi;
    }

    public void setHorairesJeudi(List<String> horairesJeudi) {
        this.horairesJeudi = horairesJeudi;
    }

    public List<String> getHorairesVendredi() {
        return horairesVendredi;
    }

    public void setHorairesVendredi(List<String> horairesVendredi) {
        this.horairesVendredi = horairesVendredi;
    }

    public List<String> getHorairesSamedi() {
        return horairesSamedi;
    }

    public void setHorairesSamedi(List<String> horairesSamedi) {
        this.horairesSamedi = horairesSamedi;
    }

    public List<String> getHorairesDimanche() {
        return horairesDimanche;
    }

    public void setHorairesDimanche(List<String> horairesDimanche) {
        this.horairesDimanche = horairesDimanche;
    }
    
    

}
