package com.example.rest;

import com.example.dto.UserDto;
import com.example.service.UserService;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author andrey
 */
@Component
public class SuccessLoginHandler implements AuthenticationSuccessHandler {
    private final UserService userService;
    
    @Autowired
    public SuccessLoginHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDto dto = userService.getCurrentUser(authentication);
        
        response.setHeader("Content-Type", "application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getOutputStream().println(new Gson().toJson(dto));
    }
    
}
