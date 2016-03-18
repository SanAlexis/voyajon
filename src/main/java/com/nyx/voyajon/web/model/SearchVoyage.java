/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.web.model;

import com.nyx.voyajon.entities.Compagnie;
import com.nyx.voyajon.entities.Trajet;
import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;

/**
 *
 * @author eisti
 */
public class SearchVoyage implements Serializable{
    
    @NotNull
    private Trajet trajet;
    private Compagnie compagnie;
    @NotNull
    private LocalDate date_depart;
    private boolean retour;
    private LocalDate date_retour;
    @NotNull
    private Byte places;

    public SearchVoyage() {
    }
    
    

    public Trajet getTrajet() {
        return trajet;
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }

    public Compagnie getCompagnie() {
        return compagnie;
    }

    public void setCompagnie(Compagnie compagnie) {
        this.compagnie = compagnie;
    }

    public LocalDate getDate_depart() {
        return date_depart;
    }

    public void setDate_depart(LocalDate date_depart) {
        this.date_depart = date_depart;
    }

    public boolean isRetour() {
        return retour;
    }

    public void setRetour(boolean retour) {
        this.retour = retour;
    }

    public LocalDate getDate_retour() {
        return date_retour;
    }

    public void setDate_retour(LocalDate date_retour) {
        this.date_retour = date_retour;
    }

    public Byte getPlaces() {
        return places;
    }

    public void setPlaces(Byte places) {
        this.places = places;
    }
    
    
    
}
