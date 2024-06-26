package com.elmenus.infrastructure.datasource.mongo.drone.repository

import com.elmenus.infrastructure.datasource.mongo.drone.model.DroneEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.UUID

@Repository
interface ReactiveDroneRepository : ReactiveCrudRepository<DroneEntity, UUID> {
    fun findByDroneName(name: String): Mono<DroneEntity>
    fun deleteByDroneId(id: UUID): Mono<Void>
}