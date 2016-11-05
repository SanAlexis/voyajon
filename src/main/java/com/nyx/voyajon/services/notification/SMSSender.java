/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.services.notification;

/**
 *
 * @author eisti
 */
public interface SMSSender {
    
    public void send(final String[] to, final String subject,final String message)  throws Exception;
    
}
