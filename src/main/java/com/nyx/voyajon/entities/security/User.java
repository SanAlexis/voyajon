/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.entities.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nyx.voyajon.entities.Employe;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author tchipi
 */
@Entity
public class User extends Employe implements UserDetails {

    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100, unique = true)
    @NaturalId
    private String username;
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String password;

    @Column
    private LocalDateTime lastaccessdate;

    @Column
    private LocalDateTime date_changementpwd;
    @Column
    private LocalDateTime date_expirationpwd;
    @Column
    private boolean enabled;
    @Column
    private byte login_attempts;

    @Enumerated(EnumType.STRING)
    private Profil profil;

    @Override
    public String getUsername() {
        return username;
    }

    public LocalDateTime getLastaccessdate() {
        return lastaccessdate;
    }

    public void setLastaccessdate(LocalDateTime lastaccessdate) {
        this.lastaccessdate = lastaccessdate;
    }

    public LocalDateTime getDate_changementpwd() {
        return date_changementpwd;
    }

    public void setDate_changementpwd(LocalDateTime date_changementpwd) {
        this.date_changementpwd = date_changementpwd;
    }

    public LocalDateTime getDate_expirationpwd() {
        return date_expirationpwd;
    }

    public void setDate_expirationpwd(LocalDateTime date_expirationpwd) {
        this.date_expirationpwd = date_expirationpwd;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte getLogin_attempts() {
        return login_attempts;
    }

    public void setLogin_attempts(byte login_attempts) {
        this.login_attempts = login_attempts;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(profil == null ? Profil.USER.name() : profil.name()));
        return authorities;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        if (lastaccessdate != null) {
            return lastaccessdate.plusYears(1).isAfter(LocalDateTime.now());
        } else {
            return true;
        }
    }

//    we will  limit login attempts in Spring Security, which means, if a user 
//     try to login with an invalid password more than 3 times, 
//       he system will lock the user and make it unable to login anymore.
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return login_attempts <= 6;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        if (date_expirationpwd != null) {
            return date_expirationpwd.isAfter(LocalDateTime.now());
        } else {
            return false;
        }

    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

}
