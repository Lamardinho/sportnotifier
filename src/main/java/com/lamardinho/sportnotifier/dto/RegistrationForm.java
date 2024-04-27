package com.lamardinho.sportnotifier.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RegistrationForm {

    private String username;

    private String password;

    private String fullname;

    private String street;

    private String city;

    private String state;

    private String zip;

    private String phone;
}
