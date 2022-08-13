package com.sedatbsp.asu.infrastructure.adapters.user.rest.dto.response;

import com.sedatbsp.asu.domain.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class UserResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    public static UserResponse fromModel(User user) {
        return UserResponse
                .builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .build();
    }

    public static List<UserResponse> fromListOfModel(List<User> users) {
        return users.stream().map(UserResponse::fromModel).collect(Collectors.toList());
    }
}
