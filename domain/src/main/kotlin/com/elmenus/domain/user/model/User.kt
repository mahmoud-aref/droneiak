package com.elmenus.domain.user.model

import java.util.UUID

data class User(
    val id: UUID,
    val username: String,
    val password: String,
    val fullName: String,
    val active: Boolean,
    val roles: List<UserRole>
) {
    fun updatePassword(password: String): User {
        return this.copy(password = password)
    }
}

enum class UserRole {
    USER, ADMIN
}
