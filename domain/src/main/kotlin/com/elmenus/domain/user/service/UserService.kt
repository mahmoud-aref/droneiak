package com.elmenus.domain.user.service

import com.elmenus.domain.user.model.User
import java.util.UUID
import java.util.concurrent.CompletableFuture

interface UserService {
    fun createUser(user: User): CompletableFuture<User>
    fun getUserById(id: UUID): CompletableFuture<User>
    fun getUserByUsername(username: String): CompletableFuture<User>
    fun getAllUsers(): CompletableFuture<List<User>>
    fun updateUser(user: User): CompletableFuture<User>
    fun updatePassword(username: String, password: String): CompletableFuture<User>
    fun deleteUser(user: User): CompletableFuture<Boolean>
}