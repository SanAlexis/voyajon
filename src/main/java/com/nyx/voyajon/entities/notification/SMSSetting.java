/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.entities.notification;

import com.nyx.voyajon.model.superclass.SimpleAuditEntity;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

/**
 *
 * @author eisti
 */
@Entity
public class SMSSetting extends SimpleAuditEntity {

    @NotNull
    @URL
    private String apiUrl;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String userParam;
    @NotNull
    private String pwdParam;
    @Lob
    private String otherParam;

    private boolean enabled;

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUserParam() {
        return userParam;
    }

    public void setUserParam(String userParam) {
        this.userParam = userParam;
    }

    public String getPwdParam() {
        return pwdParam;
    }

    public void setPwdParam(String pwdParam) {
        this.pwdParam = pwdParam;
    }

    public String getOtherParam() {
        return otherParam;
    }

    public void setOtherParam(String otherParam) {
        this.otherParam = otherParam;
    }
    
    

}
