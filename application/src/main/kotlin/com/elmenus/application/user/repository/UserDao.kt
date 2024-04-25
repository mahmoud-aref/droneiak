package com.elmenus.application.user.repository

import com.elmenus.domain.user.model.User
import com.elmenus.domain.user.repository.UserRepository
import com.elmenus.infrastructure.datasource.user.UserEntity
import com.elmenus.infrastructure.security.repository.UserReactiveRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.*
import java.util.concurrent.CompletableFuture

@Repository
class UserDao(
    private val userReactiveRepository: UserReactiveRepository
) : UserRepository {

    override fun findById(id: UUID): CompletableFuture<Optional<User>> {
        return userReactiveRepository.findById(id)
            .map { Optional.of(it.user) }
            .defaultIfEmpty(Optional.empty())
            .toFuture()
    }

    override fun findByUsername(username: String): CompletableFuture<Optional<User>> {
        return userReactiveRepository.findByUserUsername(username)
            .map { Optional.of(it.user) }
            .defaultIfEmpty(Optional.empty())
            .toFuture()
    }

    override fun findAll(): CompletableFuture<List<User>> {
        return userReactiveRepository.findAll()
            .map { it.user }
            .collectList()
            .toFuture()
    }

    override fun save(user: User): CompletableFuture<User> {
        return userReactiveRepository.save(UserEntity(user))
            .map { it.user }
            .toFuture()
    }

    override fun delete(user: User): CompletableFuture<Boolean> {
        return userReactiveRepository.delete(UserEntity(user))
            .then(Mono.defer { userReactiveRepository.existsById(user.id) })
            .toFuture()
    }

    override fun existsByUsername(username: String): CompletableFuture<Boolean> {
        return userReactiveRepository.existsByUserUsername(username)
            .toFuture()
    }

}