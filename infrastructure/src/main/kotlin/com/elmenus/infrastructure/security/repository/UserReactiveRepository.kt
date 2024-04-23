package com.elmenus.infrastructure.security.repository

import com.elmenus.infrastructure.datasource.user.UserEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface UserReactiveRepository : ReactiveCrudRepository<UserEntity, UUID> {
    fun findByUsername(username: String): Optional<UserEntity>
}