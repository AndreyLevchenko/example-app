package com.example.repository;

import com.example.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author andrey
 */
public interface UserRepository extends JpaRepository<User, Long>{

    public Optional<User> findOneByUsername(String username);
    
}

