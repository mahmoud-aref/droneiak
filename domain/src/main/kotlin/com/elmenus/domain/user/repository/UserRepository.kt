package com.elmenus.domain.user.repository

import com.elmenus.domain.user.model.User
import java.util.*
import java.util.concurrent.CompletableFuture

interface UserRepository {

    fun findById(id: UUID): CompletableFuture<Optional<User>>
    fun findByUsername(username: String): CompletableFuture<Optional<User>>
    fun findAll(): CompletableFuture<List<User>>

    fun save(user: User): CompletableFuture<User>
    fun delete(user: User): CompletableFuture<Boolean>
    fun existsByUsername(username: String): CompletableFuture<Boolean>

}