package com.example.rest;

import com.example.dto.UserDto;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author andrey
 */
@Component
public class SuccessLoginHandler implements AuthenticationSuccessHandler {
    private final UserRepository userRepository;
    
    @Autowired
    public SuccessLoginHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username = ((UserDetails)(authentication.getPrincipal())).getUsername();

        User user = userRepository.findOneByUsername(username).get();

        UserDto dto = new UserDto(username, user.getFirstName(), user.getLastName());
        
        response.setHeader("Content-Type", "application/json");
        response.getOutputStream().println(new Gson().toJson(dto));
        response.setStatus(HttpServletResponse.SC_OK);
    }
    
}
