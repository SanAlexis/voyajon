/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.services.notification;

import com.nyx.voyajon.entities.notification.SMSSetting;
import com.nyx.voyajon.exception.BusinessException;
import com.nyx.voyajon.repositories.SMSSettingRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author eisti
 */
@Service
public class SMSSenderImpl implements SMSSender {

    @Autowired
    SMSSettingRepository smsSettingRepository;

    @Override
    public void send(String[] to, String subject, String message) throws Exception {
        List<SMSSetting> smsSettings = smsSettingRepository.findAll();
        SMSSetting smsSetting = smsSettings.isEmpty() ? null : smsSettings.get(0);
        if (smsSetting == null) {
            throw new BusinessException("Aucune Configuration SMS effectué"); //To change body of generated methods, choose Tools | Templates.
        }
        
        RestTemplate  rest=new RestTemplate();
        Map<String,Object> urlParams=new HashMap();
        
    }

}
