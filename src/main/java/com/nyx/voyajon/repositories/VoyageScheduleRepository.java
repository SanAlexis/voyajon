/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.repositories;

import com.nyx.voyajon.entities.VoyageSchedule;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostFilter;

/**
 *
 * @author eisti
 */
public interface VoyageScheduleRepository extends JpaRepository<VoyageSchedule, Integer> {

    @Override
    @PostFilter("filterObject.agence.compagnie.code==principal.profil.code or hasRole('ADMIN') ")
    List<VoyageSchedule> findAll();
}
