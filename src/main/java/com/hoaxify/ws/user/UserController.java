package com.hoaxify.ws.user;

import com.hoaxify.ws.user.error.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;

    @PostMapping("/api/1.0/users")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        String username = user.getUsername();
        String displayName = user.getDisplayName();
        String password = user.getPassword();
        Map<String,String> validationErrors = new HashMap<>();
        ApiError err = new ApiError(400,"Validation Error","/api/1.0/users");
        if(!StringUtils.isEmpty(username)&&!StringUtils.isEmpty(displayName)&&!StringUtils.isEmpty(password)){
            service.save(user);
            return ResponseEntity.ok("User created!");
        }
        if(StringUtils.isEmpty(username)) {
            validationErrors.put("username","Username cannot be null.");

        }
        if(StringUtils.isEmpty(displayName)) {
            validationErrors.put("displayName","Display name cannot be null.");

        }
        if(StringUtils.isEmpty(password)) {
            validationErrors.put("password","Password cannot be null.");
        }
        err.setValidationErrors(validationErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);


    }

}
