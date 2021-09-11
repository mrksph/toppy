package com.mrksph.toppy.repositories;

import com.mrksph.toppy.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserByUsername(String s);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}
