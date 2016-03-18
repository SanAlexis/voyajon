/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.model.superclass;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nyx.voyajon.entities.security.User;
import java.io.Serializable;
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
    @JsonIgnore
    protected LocalDateTime date_modification;
    @JoinColumn(name = "code_modificateur", referencedColumnName = "code")
    @ManyToOne
    @LastModifiedBy
    @JsonIgnore
    protected User code_modificateur;
    @Column
    @CreatedDate
    @JsonIgnore
    protected LocalDateTime date_creation;
    @JoinColumn(name = "code_createur", referencedColumnName = "code")
    @ManyToOne
    @CreatedBy
    @JsonIgnore
    protected User code_createur;

    public LocalDateTime getDate_modification() {
        return date_modification;
    }

    public void setDate_modification(LocalDateTime date_modification) {
        this.date_modification = date_modification;
    }

    public User getCode_modificateur() {
        return code_modificateur;
    }

    public void setCode_modificateur(User code_modificateur) {
        this.code_modificateur = code_modificateur;
    }

    public LocalDateTime getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(LocalDateTime date_creation) {
        this.date_creation = date_creation;
    }

    public User getCode_createur() {
        return code_createur;
    }

    public void setCode_createur(User code_createur) {
        this.code_createur = code_createur;
    }

}
