package com.auth.jwt.service;

import com.auth.jwt.domain.enums.RoleEnum;
import com.auth.jwt.domain.model.Role;

public interface RoleService {
    Role getByName(RoleEnum roleEnum);
}
