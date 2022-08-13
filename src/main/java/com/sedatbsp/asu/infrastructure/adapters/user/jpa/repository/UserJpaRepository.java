package com.sedatbsp.asu.infrastructure.adapters.user.jpa.repository;

import com.sedatbsp.asu.infrastructure.adapters.user.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);


}
