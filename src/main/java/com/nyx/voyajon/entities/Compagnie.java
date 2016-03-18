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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.hibernate.validator.constraints.URL;

/**
 *
 * @author eisti
 */
@Entity
public class Compagnie  extends SimpleAuditEntity{
    
    private String libelle;
    @Column
    @URL
    private String siteweb;
    @OneToMany(mappedBy ="compagnie" )
    @JsonIgnore
    private List<Agence> agences=new ArrayList();
    @OneToMany(mappedBy ="compagnie" )
    @JsonIgnore
    private List<Tarif> tarifs=new ArrayList();

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

   

    public String getSiteweb() {
        return siteweb;
    }

    public void setSiteweb(String siteweb) {
        this.siteweb = siteweb;
    }

    public List<Agence> getAgences() {
        return agences;
    }

    public void setAgences(List<Agence> agences) {
        this.agences = agences;
    }

    public List<Tarif> getTarifs() {
        return tarifs;
    }

    public void setTarifs(List<Tarif> tarifs) {
        this.tarifs = tarifs;
    }


   
    
}
