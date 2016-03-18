/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nyx.voyajon.model.superclass.AuditEntity;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Formula;

/**
 *
 * @author eisti
 */
@Entity
public class Reservation  extends AuditEntity{
    
    @Id
    private  Integer code;
    @NotNull
    @ManyToOne
    private Voyage voyage;
    @Enumerated(EnumType.STRING)
    private ReservationMode mode;
    @NotNull
    private boolean  enabled;
    @Formula("(select count(p.code) from Passager p where p.reservation_code=code)")
    private Byte nbre_places;
    @OneToMany(mappedBy = "reservation",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Passager>  passagers;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

   

    public Voyage getVoyage() {
        return voyage;
    }

    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
    }

    

    public ReservationMode getMode() {
        return mode;
    }

    public void setMode(ReservationMode mode) {
        this.mode = mode;
    }

   

    public Byte getNbre_places() {
        return nbre_places;
    }

    public void setNbre_places(Byte nbre_places) {
        this.nbre_places = nbre_places;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Passager> getPassagers() {
        return passagers;
    }

    public void setPassagers(List<Passager> passagers) {
        this.passagers = passagers;
    }

  

  
   
    

    
}
