package com.sedatbsp.asu.infrastructure.configuration.security;

import com.sedatbsp.asu.domain.common.exception.UrlBusinessException;
import com.sedatbsp.asu.infrastructure.adapters.user.jpa.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserJpaRepository userJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userJpaRepository.findByUsername(username)
                .orElseThrow(() -> new UrlBusinessException("user.not.found"));
    }

}
