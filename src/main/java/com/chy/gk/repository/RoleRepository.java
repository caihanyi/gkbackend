package com.chy.gk.repository;

import com.chy.gk.model.uesr.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleRepository extends JpaRepository<Role, Long> , JpaSpecificationExecutor {
}
