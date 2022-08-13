package com.sedatbsp.asu.infrastructure.adapters.user.rest.dto.request;

import com.sedatbsp.asu.domain.user.command.UserLogin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotNull(message = "{url.constraint.username.NotNull.message}")
    @Size(min = 4, max = 32, message = "{url.constraint.username.Size.message}")
    private String username;

    @NotNull(message = "{url.constraint.password.NotNull.message}")
    @Size(min = 4, max = 32, message = "{url.constraint.password.Size.message}")
    private String password;

    public UserLogin toModel() {
        return UserLogin
                .builder()
                .username(username)
                .password(password)
                .build();
    }
}
