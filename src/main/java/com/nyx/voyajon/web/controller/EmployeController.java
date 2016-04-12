package com.nyx.voyajon.web.controller;

import com.nyx.voyajon.entities.Chauffeur;
import com.nyx.voyajon.entities.security.Profil;
import com.nyx.voyajon.repositories.ChauffeurRepository;
import com.nyx.voyajon.repositories.UserRepository;
import com.nyx.voyajon.entities.security.User;
import com.nyx.voyajon.services.UserService;
import com.nyx.voyajon.utils.SecurityUtils;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/app")
public class EmployeController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChauffeurRepository chauffeurRepository;

    @RequestMapping(value = "/login", method = GET)
    ResponseEntity<String> isCurrentUserLoggedIn() {
        return new ResponseEntity<>(SecurityUtils.getCurrentUser() != null ? SecurityUtils.getCurrentUserName() : null,
                SecurityUtils.getCurrentUser() != null ? OK : UNAUTHORIZED);
    }

    @RequestMapping(value = "/init", method = GET)
    ResponseEntity<User> init() {
        User u = new User();
        u.setProfil(Profil.ADMIN);
        u.setUsername("admin");
        u.setPassword(u.getUsername());
        u.setNom_prenom(u.getUsername());
        return saveUser(u);
    }

    @RequestMapping(value = "/User", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listUser() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/User/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> findUser(@PathVariable Integer id) {
        User v = userRepository.findOne(id);
        return new ResponseEntity<>(v != null ? v : new User(), HttpStatus.OK);
    }

    @RequestMapping(value = "/User", method = RequestMethod.POST)
    public ResponseEntity<User> saveUser(@RequestBody User u) {
        if (u.getCode() == null) {
            ShaPasswordEncoder spe = new ShaPasswordEncoder();
            u.setPassword(spe.encodePassword(u.getPassword(), null));
            u.setEnabled(true);
            u.setDate_changementpwd(LocalDateTime.now());
            u.setDate_expirationpwd(u.getDate_changementpwd().plusMonths(6));
        }
        return new ResponseEntity<>(userRepository.save(u), HttpStatus.OK);
    }

    @RequestMapping(value = "/User/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delUser(@PathVariable Integer id) throws Exception {
        userRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/Chauffeur", method = RequestMethod.GET)
    public ResponseEntity<List<Chauffeur>> listChauffeur() {
        return new ResponseEntity<>(chauffeurRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/Chauffeur/{id}", method = RequestMethod.GET)
    public ResponseEntity<Chauffeur> findChauffeur(@PathVariable Integer id) {
        Chauffeur v = chauffeurRepository.findOne(id);
        return new ResponseEntity<>(v != null ? v : new Chauffeur(), HttpStatus.OK);
    }

    @RequestMapping(value = "/Chauffeur", method = RequestMethod.POST)
    public ResponseEntity<Chauffeur> saveChauffeur(@RequestBody Chauffeur v) {
        return new ResponseEntity<>(chauffeurRepository.save(v), HttpStatus.OK);
    }

    @RequestMapping(value = "/Chauffeur/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delChauffeur(@PathVariable Integer id) throws Exception {
        chauffeurRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
