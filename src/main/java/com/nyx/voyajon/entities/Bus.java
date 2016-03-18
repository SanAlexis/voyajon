/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.entities;

import com.nyx.voyajon.model.superclass.SimpleAuditEntity;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.NaturalId;


/**
 *
 * @author eisti
 */
@Entity
public class Bus  extends SimpleAuditEntity{
    
    @NaturalId
    private String immatriculation;
    private String marque;
    @NotNull
    private Byte  nbre_places;
    @Enumerated(EnumType.STRING)
    private BusClasse classe;
    @ManyToOne
    @NotNull
    private Chauffeur chauffeur1;
    @ManyToOne
    private Chauffeur chauffeur2;
    @ManyToOne
    private Compagnie compagnie;
    private boolean enabled;

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

   

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public Byte getNbre_places() {
        return nbre_places;
    }

    public void setNbre_places(Byte nbre_places) {
        this.nbre_places = nbre_places;
    }

    public BusClasse getClasse() {
        return classe;
    }

    public void setClasse(BusClasse classe) {
        this.classe = classe;
    }

    public Chauffeur getChauffeur1() {
        return chauffeur1;
    }

    public void setChauffeur1(Chauffeur chauffeur1) {
        this.chauffeur1 = chauffeur1;
    }

    public Chauffeur getChauffeur2() {
        return chauffeur2;
    }

    public void setChauffeur2(Chauffeur chauffeur2) {
        this.chauffeur2 = chauffeur2;
    }

    public Compagnie getCompagnie() {
        return compagnie;
    }

    public void setCompagnie(Compagnie compagnie) {
        this.compagnie = compagnie;
    }

   

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    
    
    
    
    
}
