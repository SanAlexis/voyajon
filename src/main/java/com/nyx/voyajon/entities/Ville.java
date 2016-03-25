/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.entities;

import com.nyx.voyajon.model.superclass.SimpleAuditEntity;
import javax.persistence.Entity;

/**
 *
 * @author eisti
 */
@Entity
public class Ville extends SimpleAuditEntity{
    
    private String libelle;

    public Ville() {
    }
    
    

    public Ville(String nom) {
        this.libelle=nom;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    
    
}
