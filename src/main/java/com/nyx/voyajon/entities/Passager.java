/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author eisti
 */

@Entity
public class Passager  extends Personne{
    
    private Byte numero_siege;
    @ManyToOne
    @NotNull
    private Reservation reservation;
    @OneToOne(mappedBy = "passager")
    private VoyageFeedback  feedback;
    @Enumerated(EnumType.STRING)
    private PassagerStatut statut;

    public Byte getNumero_siege() {
        return numero_siege;
    }

    public void setNumero_siege(Byte numero_siege) {
        this.numero_siege = numero_siege;
    }

  

    public VoyageFeedback getFeedback() {
        return feedback;
    }

    public void setFeedback(VoyageFeedback feedback) {
        this.feedback = feedback;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public PassagerStatut getStatut() {
        return statut;
    }

    public void setStatut(PassagerStatut statut) {
        this.statut = statut;
    }

    
    
}
