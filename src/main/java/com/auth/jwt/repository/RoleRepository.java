package com.auth.jwt.repository;

import com.auth.jwt.enums.RoleEnum;
import com.auth.jwt.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Boolean existsByName(RoleEnum name);
}
