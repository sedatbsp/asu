package com.sedatbsp.asu.infrastructure.adapters.user.jpa.adapter;

import com.sedatbsp.asu.domain.common.exception.UrlBusinessException;
import com.sedatbsp.asu.domain.user.command.UserCreate;
import com.sedatbsp.asu.domain.user.command.UserLogin;
import com.sedatbsp.asu.domain.user.model.User;
import com.sedatbsp.asu.domain.user.port.UserRepository;
import com.sedatbsp.asu.infrastructure.adapters.user.jpa.entity.UserEntity;
import com.sedatbsp.asu.infrastructure.adapters.user.jpa.repository.UserJpaRepository;
import com.sedatbsp.asu.infrastructure.configuration.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryJpaAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserCreate userCreate) {

        if(userJpaRepository.findByUsername(userCreate.getUsername()).isPresent()) {
            throw new UrlBusinessException("user.username.exists");
        }
        else if(userJpaRepository.findByEmail(userCreate.getEmail()).isPresent()) {
            throw new UrlBusinessException("user.email.exists");
        }

        var userEntity = new UserEntity();
        userEntity.setUsername(userCreate.getUsername());
        userEntity.setFirstName(userCreate.getFirstName());
        userEntity.setLastName(userCreate.getLastName());
        userEntity.setEmail(userCreate.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userCreate.getPassword()));


        return toModel(userJpaRepository.save(userEntity));
    }

    @Override
    public String login(UserLogin userLogin) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword()));
            var userEntity = userJpaRepository
                    .findByUsername(userLogin.getUsername())
                    .orElseThrow(() -> new UrlBusinessException("user.not.found"));
            return jwtProvider.generateToken(userLogin.getUsername(), userEntity.getId());
        } catch (AuthenticationException authenticationException) {
            throw new UrlBusinessException("invalid.username.or.password");
        }
    }

    private User toModel(UserEntity userEntity) {
        return User
                .builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdatedAt())
                .status(userEntity.getStatus())
                .build();
    }
}
