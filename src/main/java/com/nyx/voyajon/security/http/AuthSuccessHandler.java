package com.nyx.voyajon.security.http;

import com.nyx.voyajon.entities.security.User;
import com.nyx.voyajon.services.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static final Logger LOGGER = Logger.getLogger(AuthSuccessHandler.class.getName());

   

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        //response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        LOGGER.log(Level.INFO, "{0} got is connected ", authentication.getName());
        PrintWriter writer = response.getWriter();
        writer.write(authentication.getName());
        JSONObject jo = new JSONObject();
        jo.put("username", authentication.getName());
        writer.write(jo.toJSONString());
        writer.flush();
    }
}
