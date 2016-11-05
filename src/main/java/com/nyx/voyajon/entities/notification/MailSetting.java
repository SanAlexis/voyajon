/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.entities.notification;

import com.nyx.voyajon.model.superclass.SimpleAuditEntity;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 *
 * @author eisti
 */
@Entity
public class MailSetting extends SimpleAuditEntity {

    @NotNull
    private String hostname;
    @NotNull
    private Integer port;
    @NotNull
    private String username;
    @NotNull
    private String password;

    private boolean enabled;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
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

}
