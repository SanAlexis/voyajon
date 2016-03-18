/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.entities;

import com.nyx.voyajon.model.superclass.SimpleAuditEntity;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author eisti
 */
@Entity
public class VoyageFeedback  extends SimpleAuditEntity{
    
    @OneToOne
    @NotNull
    private Passager passager;
    @Min(0)
    @Max(1)
    private Byte note;
    private String commentaire;

    public Passager getPassager() {
        return passager;
    }

    public void setPassager(Passager passager) {
        this.passager = passager;
    }

    public Byte getNote() {
        return note;
    }

    public void setNote(Byte note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    
    
    
}
