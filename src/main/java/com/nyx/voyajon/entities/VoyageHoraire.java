///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.nyx.voyajon.entities;
//
//import com.nyx.voyajon.model.superclass.SimpleAuditEntity;
//import java.time.DayOfWeek;
//import java.time.LocalTime;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.ManyToOne;
//import javax.validation.constraints.NotNull;
//
///**
// *
// * @author eisti
// */
//@Entity
//public class VoyageHoraire extends SimpleAuditEntity{
//    
//    @NotNull
//    private LocalTime heure;
//    @NotNull
//    @Enumerated(EnumType.STRING)
//    private DayOfWeek  jour;
//    @ManyToOne
//    @NotNull
//    private VoyageSchedule  schedule;
//
//    public LocalTime getHeure() {
//        return heure;
//    }
//
//    public void setHeure(LocalTime heure) {
//        this.heure = heure;
//    }
//
//    public DayOfWeek getJour() {
//        return jour;
//    }
//
//    public void setJour(DayOfWeek jour) {
//        this.jour = jour;
//    }
//
//    public VoyageSchedule getSchedule() {
//        return schedule;
//    }
//
//    public void setSchedule(VoyageSchedule schedule) {
//        this.schedule = schedule;
//    }
//    
//    
//    
//}
