/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.entities.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nyx.voyajon.model.superclass.SimpleAuditEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import org.hibernate.annotations.NaturalId;

/**
 *
 * @author eisti
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Profil   extends SimpleAuditEntity{
    
    @NaturalId
    private String libelle;
    
    @OneToMany(mappedBy = "profil")
    @JsonIgnore
    private List<User> users=new ArrayList();

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    
    
}
