/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.entities;

import com.nyx.voyajon.model.superclass.SimpleAuditEntity;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 *
 * @author eisti
 */
@MappedSuperclass
public class Personne   extends SimpleAuditEntity{
    
    @NotNull
    private String nom_prenom;
    private Integer telephone;
    private String email;
    private Integer numero_cni;

    public String getNom_prenom() {
        return nom_prenom;
    }

    public void setNom_prenom(String nom_prenom) {
        this.nom_prenom = nom_prenom;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNumero_cni() {
        return numero_cni;
    }

    public void setNumero_cni(Integer numero_cni) {
        this.numero_cni = numero_cni;
    }
    
    
    
    
}
