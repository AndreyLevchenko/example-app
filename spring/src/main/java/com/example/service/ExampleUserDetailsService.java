package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 *
 * @author andrey
 */
@Component
public class ExampleUserDetailsService implements UserDetailsService{
    private UserRepository userRepository;

    @Autowired
    public ExampleUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(final String username) {
        Optional<User> userFromDatabase = userRepository.findOneByUsername(username);

        return userFromDatabase.map( new Function<User, UserDetails>(){
            @Override
            public UserDetails apply(User user) {
                return new org.springframework.security.core.userdetails.User(username, user.getPassword(), Collections.EMPTY_SET);
            }
            
        }).orElseThrow(() -> new UsernameNotFoundException ("User " + username + " was not found in the database"));
    }
    
    
}
