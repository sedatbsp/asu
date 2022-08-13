package com.sedatbsp.asu.domain.user.facade;

import com.sedatbsp.asu.domain.user.command.UserCreate;
import com.sedatbsp.asu.domain.user.command.UserLogin;
import com.sedatbsp.asu.domain.user.model.User;
import com.sedatbsp.asu.domain.user.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;

    @Transactional
    public User createUser(UserCreate userCreate) {
        return userRepository.createUser(userCreate);
    }

    @Transactional
    public String login(UserLogin userLogin) {
        return userRepository.login(userLogin);
    }
}
