package com.chy.gk.repository;

import com.chy.gk.model.uesr.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PermissionRepository extends JpaRepository<Permission, Long> , JpaSpecificationExecutor {
}
