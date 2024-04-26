package com.elmenus.presentation.rest.model

import com.elmenus.infrastructure.datasource.mongo.user.model.UserEntity

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
                UserEntity.getUsername(userEntity),
                UserEntity.getFullName(userEntity),
                UserEntity.getRoles(userEntity)
            )
        }
    }
}