package com.example.rest;

import com.example.dto.SignupRequestDto;
import com.example.dto.UserDto;
import com.example.service.UserService;
import java.util.Collections;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author andrey
 */
@RestController
@RequestMapping("/api/users")
public class UserResource {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public UserResource(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }
    
    
    @RequestMapping(value="current", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto current() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.getCurrentUser(authentication);
    }
    
    @RequestMapping(value="signup", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody SignupRequestDto signupRequest){
        //TODO persist user 
        
        if (signupRequest.getDoLogin()) {
            UserDetails userDetails = new org.springframework.security.core.userdetails.User(signupRequest.getUsername(), signupRequest.getPassword(), Collections.EMPTY_SET);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, signupRequest.getPassword(), userDetails.getAuthorities());

            authenticationManager.authenticate(usernamePasswordAuthenticationToken);   

            if (usernamePasswordAuthenticationToken.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
    }
    
    
}
