package com.airbnb.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AppUserDto {

    private Long id;

    @Size(min = 2,message = "Should be 2 or more characters")
    private String name;

    @Email(message = "Invalid email format")
    private String email;


    private String username;

    private String password;

    private String role;

}