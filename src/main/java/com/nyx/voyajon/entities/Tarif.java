/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.entities;

import com.nyx.voyajon.model.superclass.SimpleAuditEntity;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author eisti
 */
@Entity
public class Tarif  extends SimpleAuditEntity{
    
    @NotNull
    @ManyToOne
    private Compagnie compagnie;
    @NotNull
    @ManyToOne
    private Trajet trajet;
    @NotNull
    private BigDecimal prix_classic_aller;
    @NotNull
    private BigDecimal prix_classic_aller_retour;
    @NotNull
    private BigDecimal prix_vip_aller;
    @NotNull
    private BigDecimal prix_vip_aller_retour;
  

   

    public Trajet getTrajet() {
        return trajet;
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }

    
   
    public Compagnie getCompagnie() {
        return compagnie;
    }

    public void setCompagnie(Compagnie compagnie) {
        this.compagnie = compagnie;
    }

    public BigDecimal getPrix_classic_aller() {
        return prix_classic_aller;
    }

    public void setPrix_classic_aller(BigDecimal prix_classic_aller) {
        this.prix_classic_aller = prix_classic_aller;
    }

    public BigDecimal getPrix_classic_aller_retour() {
        return prix_classic_aller_retour;
    }

    public void setPrix_classic_aller_retour(BigDecimal prix_classic_aller_retour) {
        this.prix_classic_aller_retour = prix_classic_aller_retour;
    }

    public BigDecimal getPrix_vip_aller() {
        return prix_vip_aller;
    }

    public void setPrix_vip_aller(BigDecimal prix_vip_aller) {
        this.prix_vip_aller = prix_vip_aller;
    }

    public BigDecimal getPrix_vip_aller_retour() {
        return prix_vip_aller_retour;
    }

    public void setPrix_vip_aller_retour(BigDecimal prix_vip_aller_retour) {
        this.prix_vip_aller_retour = prix_vip_aller_retour;
    }

   
   
    
    
    
}
