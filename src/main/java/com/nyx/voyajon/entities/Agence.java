/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nyx.voyajon.model.superclass.SimpleAuditEntity;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;



/**
 *
 * @author eisti
 */
@Entity
public class Agence  extends SimpleAuditEntity{
 
    @NotNull
    private String libelle;
    @NotNull
    @ManyToOne
    private Compagnie compagnie;
    @NotNull
    @ManyToOne
    private Ville ville;
    private String boite_postale;
    private String adresse;
    private String telephone1;
    private String telephone2;
    
   

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    

   

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public Compagnie getCompagnie() {
        return compagnie;
    }

    public void setCompagnie(Compagnie compagnie) {
        this.compagnie = compagnie;
    }

   

    public String getBoite_postale() {
        return boite_postale;
    }

    public void setBoite_postale(String boite_postale) {
        this.boite_postale = boite_postale;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }
    
    
    
}
