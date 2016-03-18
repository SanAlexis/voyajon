/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.entities;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author eisti
 */
@MappedSuperclass
public class Employe  extends Personne{
    
    @ManyToOne
    private Compagnie compagnie;

    public Compagnie getCompagnie() {
        return compagnie;
    }

    public void setCompagnie(Compagnie compagnie) {
        this.compagnie = compagnie;
    }
    
    

   
    
    
}
