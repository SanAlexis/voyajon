/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.repositories;

import com.nyx.voyajon.entities.notification.SMSSetting;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author eisti
 */
public interface SMSSettingRepository  extends JpaRepository<SMSSetting, Integer>{
 
   
    public SMSSetting findByEnabled(boolean  enabled);
    
    
  
}
