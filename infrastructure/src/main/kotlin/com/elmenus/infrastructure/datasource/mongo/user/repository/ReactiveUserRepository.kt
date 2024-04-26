package com.elmenus.infrastructure.datasource.mongo.user.repository

import com.elmenus.infrastructure.datasource.mongo.user.model.UserEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.UUID

@Repository
interface ReactiveUserRepository : ReactiveCrudRepository<UserEntity, UUID> {
    fun findByUserUsername(username: String): Mono<UserEntity>
    fun existsByUserUsername(username: String): Mono<Boolean>
}