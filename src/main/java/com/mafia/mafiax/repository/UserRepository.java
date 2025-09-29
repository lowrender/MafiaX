package com.mafia.mafiax.repository;

import com.mafia.mafiax.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String username);

}
