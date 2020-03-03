package com.chy.gk.repository;

import com.chy.gk.model.uesr.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
