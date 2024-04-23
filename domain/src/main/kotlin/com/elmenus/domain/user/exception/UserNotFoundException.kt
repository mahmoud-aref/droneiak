package com.elmenus.domain.user.exception

class UserNotFoundException : RuntimeException() {
    override val message: String
        get() = "User not found"
}