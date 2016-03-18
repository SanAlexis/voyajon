/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.services;

import com.nyx.voyajon.entities.Trajet;

/**
 *
 * @author eisti
 */
public interface TrajetService {
    
    public Trajet save (Trajet t) throws Exception;
    
    public Trajet getReverseTrajet (Trajet t) throws Exception;
    
    public Trajet createReverseTrajet(Trajet tr) throws Exception;
    
}
