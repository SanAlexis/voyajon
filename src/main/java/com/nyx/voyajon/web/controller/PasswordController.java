package com.nyx.voyajon.web.controller;

import com.nyx.voyajon.entities.security.User;
import com.nyx.voyajon.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value = "/app")
public class PasswordController {


    private final UserService userService;
 

    @Autowired
    PasswordController(UserService userService) {
        this.userService = userService;
       
    }


    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    public ResponseEntity<String> changepassword(
            @RequestParam String oldpassword,
            @RequestParam String newpassword) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return changepwd(oldpassword, newpassword, principal, 6);
    }

    private ResponseEntity<String> changepwd(String oldpassword, String newpassword,
            User principal, int calendaramount) {
        try {

            ShaPasswordEncoder encoder = new ShaPasswordEncoder();
            if (!encoder.isPasswordValid(principal.getPassword(), oldpassword, null)) {
                return new ResponseEntity<>("Ancien Mot de passe incorrect", INTERNAL_SERVER_ERROR);
            }
            if (oldpassword.equalsIgnoreCase(newpassword)) {
                return new ResponseEntity<>("Ancien Mot de passe identique au nouveau", INTERNAL_SERVER_ERROR);
            }

            userService.changeUserPassword(principal, newpassword, calendaramount);

            return new ResponseEntity<>("", OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
        }
    }

}
