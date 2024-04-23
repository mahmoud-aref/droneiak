package com.elmenus.presentation.rest.model

import com.elmenus.infrastructure.datasource.user.UserEntity


data class UserRegistrationRequest(
    val username: String,
    val password: String,
    val fullName: String,
    val roles: List<String>,
) {
    fun toUserEntity(): UserEntity {
        return UserEntity.fromRequest(username, password, fullName, roles)
    }
}
