package com.sedatbsp.asu.domain.user.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreate {

    private String firstName;
    private String lastName;

    private String username;
    private String email;
    private String password;
}
