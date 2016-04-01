/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.web.model;

import java.io.Serializable;

/**
 *
 * @author eisti
 */
public class MyBookingDTO  implements Serializable{
    
    private String telephone;
    private String email;
    private Integer reservation;

    public MyBookingDTO() {
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getReservation() {
        return reservation;
    }

    public void setReservation(Integer reservation) {
        this.reservation = reservation;
    }
    
    
    
}
