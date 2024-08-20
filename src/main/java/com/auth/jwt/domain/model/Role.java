package com.auth.jwt.domain.model;

import com.auth.jwt.domain.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name="name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "role")
    private List<User> users;
}
