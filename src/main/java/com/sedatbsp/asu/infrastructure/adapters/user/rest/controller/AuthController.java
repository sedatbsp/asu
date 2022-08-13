package com.sedatbsp.asu.infrastructure.adapters.user.rest.controller;

import com.sedatbsp.asu.domain.user.facade.UserFacade;
import com.sedatbsp.asu.infrastructure.adapters.user.rest.dto.request.LoginRequest;
import com.sedatbsp.asu.infrastructure.common.annotation.ApiController;
import com.sedatbsp.asu.infrastructure.common.rest.BaseController;
import com.sedatbsp.asu.infrastructure.common.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@ApiController
@RequiredArgsConstructor
public class AuthController extends BaseController {

    private final UserFacade userFacade;

    @PostMapping("login")
    public Response<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        final String response = userFacade.login(loginRequest.toModel());
        return respond(response);
    }

}
