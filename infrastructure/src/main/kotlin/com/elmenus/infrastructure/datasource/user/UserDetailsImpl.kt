package com.elmenus.infrastructure.datasource.user

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User


class UserDetailsImpl(private val userEntity: UserEntity) : User(
    userEntity.user.username,
    userEntity.user.password,
    userEntity.user.active,
    userEntity.user.active,
    userEntity.user.active,
    userEntity.user.active,
    userEntity.user.roles.map { role -> GrantedAuthority { role.name } }
        .map { it }.toMutableList()
)