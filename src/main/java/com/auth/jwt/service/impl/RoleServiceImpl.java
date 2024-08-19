package com.auth.jwt.service.impl;

import com.auth.jwt.domain.enums.RoleEnum;
import com.auth.jwt.domain.model.Role;
import com.auth.jwt.repository.RoleRepository;
import com.auth.jwt.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public Role getByName(RoleEnum roleEnum){
        return roleRepository.findByName(roleEnum);
    }
}
