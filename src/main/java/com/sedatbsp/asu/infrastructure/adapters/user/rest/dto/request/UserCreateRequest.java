package com.sedatbsp.asu.infrastructure.adapters.user.rest.dto.request;

import com.sedatbsp.asu.domain.user.command.UserCreate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    private String firstName;
    private String lastName;

    private String username;
    private String email;
    private String password;

    public UserCreate toModel(UserCreateRequest userCreateRequest) {
        return UserCreate
                .builder()
                .firstName(userCreateRequest.firstName)
                .lastName(userCreateRequest.lastName)
                .username(userCreateRequest.username)
                .email(userCreateRequest.email)
                .password(userCreateRequest.password)
                .build();
    }

}
