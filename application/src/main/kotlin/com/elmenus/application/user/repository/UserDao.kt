package com.elmenus.application.user.repository

import com.elmenus.domain.user.model.User
import com.elmenus.domain.user.repository.UserRepository
import com.elmenus.infrastructure.datasource.user.UserEntity
import com.elmenus.infrastructure.security.repository.UserReactiveRepository
import org.springframework.stereotype.Repository
import java.util.*
import java.util.concurrent.CompletableFuture

@Repository
class UserDao(
    private val userReactiveRepository: UserReactiveRepository
) : UserRepository {

    override fun findById(id: UUID): CompletableFuture<Optional<User>> {
        TODO("Not yet implemented")
    }

    override fun findByUsername(username: String): CompletableFuture<Optional<User>> {
        TODO("Not yet implemented")
    }

    override fun findAll(): CompletableFuture<Optional<List<User>>> {
        TODO("Not yet implemented")
    }

    override fun save(user: User): CompletableFuture<User> {
        return userReactiveRepository.save(UserEntity(user))
            .map { it.user }
            .toFuture()
    }

    override fun delete(user: User): CompletableFuture<Boolean> {
        TODO("Not yet implemented")
    }

    override fun existsByUsername(username: String): CompletableFuture<Boolean> {
        TODO("Not yet implemented")
    }

}