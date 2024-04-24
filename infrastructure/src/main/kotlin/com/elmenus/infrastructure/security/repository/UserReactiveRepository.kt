package com.elmenus.infrastructure.security.repository

import com.elmenus.infrastructure.datasource.user.UserEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.UUID

@Repository
interface UserReactiveRepository : ReactiveCrudRepository<UserEntity, UUID> {
    fun findByUserUsername(username: String): Mono<UserEntity>
}