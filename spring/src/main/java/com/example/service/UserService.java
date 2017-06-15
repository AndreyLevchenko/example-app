package com.example.service;

import com.example.dto.UserDto;
import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 *
 * @author andrey
 */
@Component
public class UserService {
    private final UserRepository userRepository;
    
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    
    
    public UserDto getCurrentUser(Authentication authentication){
        
        String username = ((UserDetails)(authentication.getPrincipal())).getUsername();

        User user = userRepository.findOneByUsername(username).get();

        return new UserDto(username, user.getFirstName(), user.getLastName());
    }
    
}
