package com.elmenus.presentation.rest.model

import com.elmenus.infrastructure.datasource.user.UserEntity

data class UserRegistrationResponse(
    val id: String,
    val username: String,
    val fullName: String,
    val roles: List<String>
) {
    companion object {
        fun fromEntity(userEntity: UserEntity): UserRegistrationResponse {
            return UserRegistrationResponse(
                UserEntity.getId(userEntity),
                userEntity.username,
                UserEntity.getFullName(userEntity),
                UserEntity.getRoles(userEntity)
            )
        }
    }
}