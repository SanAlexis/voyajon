/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.model.superclass;

import com.nyx.voyajon.entities.security.User;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author tchipi
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity implements Serializable {

    @LastModifiedDate
    @Column(name = "date_modification")
    protected Instant dateModification;
    @CreatedDate
    @Column(name = "date_creation")
    protected Instant dateCreation;
    @JoinColumn(name = "code_modificateur", referencedColumnName = "code")
    @ManyToOne
    @LastModifiedBy
    protected User code_modificateur;
    @JoinColumn(name = "code_createur", referencedColumnName = "code")
    @ManyToOne
    @CreatedBy
    protected User code_createur;

    
    public User getCode_modificateur() {
        return code_modificateur;
    }

    public void setCode_modificateur(User code_modificateur) {
        this.code_modificateur = code_modificateur;
    }

  
    public User getCode_createur() {
        return code_createur;
    }

    public void setCode_createur(User code_createur) {
        this.code_createur = code_createur;
    }

    public Instant getDateModification() {
        return dateModification;
    }

    public void setDateModification(Instant dateModification) {
        this.dateModification = dateModification;
    }

    public Instant getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Instant dateCreation) {
        this.dateCreation = dateCreation;
    }

   
    
}
