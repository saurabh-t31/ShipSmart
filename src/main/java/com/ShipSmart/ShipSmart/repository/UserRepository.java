package com.ShipSmart.ShipSmart.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ShipSmart.ShipSmart.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}