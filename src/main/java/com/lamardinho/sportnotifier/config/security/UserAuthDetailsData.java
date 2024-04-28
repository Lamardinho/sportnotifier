package com.lamardinho.sportnotifier.config.security;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Data
class UserAuthDetailsData implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private Set<String> authorities;
}
