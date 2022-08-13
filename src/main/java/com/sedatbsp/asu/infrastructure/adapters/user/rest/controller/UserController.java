package com.sedatbsp.asu.infrastructure.adapters.user.rest.controller;

import com.sedatbsp.asu.domain.user.facade.UserFacade;
import com.sedatbsp.asu.infrastructure.adapters.user.rest.dto.request.UserCreateRequest;
import com.sedatbsp.asu.infrastructure.adapters.user.rest.dto.response.UserResponse;
import com.sedatbsp.asu.infrastructure.common.annotation.SecureApiController;
import com.sedatbsp.asu.infrastructure.common.rest.BaseController;
import com.sedatbsp.asu.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@SecureApiController
@RequiredArgsConstructor
public class UserController extends BaseController {

    private final UserFacade userFacade;

    @PostMapping("user")
    public Response<?> createUser(@RequestBody @Valid UserCreateRequest userCreateRequest) {
        var user = userFacade.createUser(userCreateRequest.toModel(userCreateRequest));
        return respond(UserResponse.fromModel(user));
    }


}
