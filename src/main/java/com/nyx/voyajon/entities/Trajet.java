/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nyx.voyajon.model.superclass.SimpleAuditEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author eisti
 */
@Entity
public class Trajet extends SimpleAuditEntity {

    @NotNull
    private String libelle;
    @ManyToOne
    @NotNull
    private Ville villeA;
    @ManyToOne
    @NotNull
    private Ville villeB;
    private Short duree;

    @OneToMany(mappedBy = "trajet")
    @JsonIgnore
    private List<Tarif> tarifs=new ArrayList();

    public Short getDuree() {
        return duree;
    }

    public void setDuree(Short duree) {
        this.duree = duree;
    }

    public List<Tarif> getTarifs() {
        return tarifs;
    }

    public Ville getVilleA() {
        return villeA;
    }

    public void setVilleA(Ville villeA) {
        this.villeA = villeA;
    }

    public Ville getVilleB() {
        return villeB;
    }

    public void setVilleB(Ville villeB) {
        this.villeB = villeB;
    }

    

    public void setTarifs(List<Tarif> tarifs) {
        this.tarifs = tarifs;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

}
