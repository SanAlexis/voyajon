/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.security;

import com.nyx.voyajon.entities.security.User;
import com.nyx.voyajon.utils.SecurityUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

/**
 *
 * @author eisti
 */
@Component
public class AuditorAwareImpl implements AuditorAware<User> {

    @Override
    public User getCurrentAuditor() {
        return SecurityUtils.getCurrentUser();
    }

}
