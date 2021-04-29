package com.hoaxify.ws.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;
    @NotNull(message = "Username cannot be null")
    @Size(min = 4, max = 255, message = "Username must be between 4-255 characters.")
    private String username;
    @NotNull(message = "Display name cannot be null")
    @Size(min = 4, max = 255, message = "Display name must be between 4-255 characters.")
    private String displayName;
    @NotNull(message = "Password cannot be null")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,16}$", message = "Password must be between 8-16 characters with at least one digit,one lower case letter and one upper case letter.")
    private String password;


}
