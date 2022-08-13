package com.sedatbsp.asu.domain.user.port;

import com.sedatbsp.asu.domain.user.command.UserCreate;
import com.sedatbsp.asu.domain.user.command.UserLogin;
import com.sedatbsp.asu.domain.user.model.User;

public interface UserRepository {
    User createUser(UserCreate userCreate);

    String login(UserLogin userLogin);
}
