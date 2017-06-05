package com.example.rest;

import com.example.model.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author andrey
 */
@RestController
@RequestMapping("/api/tests")
public class ExampleResource {

    public ExampleResource() {
    }

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUsers() {
        List<User> result = new ArrayList<>();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
