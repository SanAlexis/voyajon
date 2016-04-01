/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyx.voyajon.config;

import com.nyx.voyajon.security.http.AuthFailureHandler;
import com.nyx.voyajon.security.http.AuthSuccessHandler;
import com.nyx.voyajon.security.http.CustomDaoAuthenticationProvider;
import com.nyx.voyajon.security.http.HttpAuthenticationEntryPoint;
import com.nyx.voyajon.security.http.HttpLogoutSuccessHandler;
import com.nyx.voyajon.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author tchipi
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private HttpAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private AuthSuccessHandler authSuccessHandler;
    @Autowired
    private AuthFailureHandler authFailureHandler;
    @Autowired
    private HttpLogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private UserService us;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() throws Exception {
        CustomDaoAuthenticationProvider authenticationProvider = new CustomDaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(us);
        authenticationProvider.setPasswordEncoder(new ShaPasswordEncoder());
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public void configure(WebSecurity builder) throws Exception {
        builder
                .ignoring()
                .antMatchers("/app/css/**",
                        "/app/fonts/**",
                        "/app/img/**",
                        "/app/js/**",
                        "/app/l10n/**",
                        "/app/tpl/**",
                        "/app/libs/**",
                        "/app/landing.html",
                        "/app/index.html");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                        .authenticationProvider(authenticationProvider())
                        .exceptionHandling()
                        .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                        .formLogin()
                        .loginProcessingUrl("/app/connect")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .successHandler(authSuccessHandler)
                        .failureHandler(authFailureHandler)
                        .permitAll()
                .and()
                        .logout()
                        .logoutUrl("/app/signout").invalidateHttpSession(true)
                        .logoutSuccessHandler(logoutSuccessHandler)
                         .permitAll()
                .and()
                        .csrf().disable()
                        .authorizeRequests() 
                        .antMatchers("/app/client/**").permitAll()
                        .antMatchers("/app/init").permitAll()
                         .antMatchers(HttpMethod.POST, "/app/forgotpassword").permitAll()
                         .antMatchers(HttpMethod.POST, "/app/changepasswordexp").permitAll()
                         .anyRequest().authenticated()
                .and()
                        .sessionManagement()
                        .maximumSessions(1);
    }
    

    
}
