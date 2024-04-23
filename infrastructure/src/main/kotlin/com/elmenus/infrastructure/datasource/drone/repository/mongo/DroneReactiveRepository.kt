package com.elmenus.infrastructure.datasource.drone.repository.mongo

import com.elmenus.infrastructure.datasource.drone.model.DroneEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.UUID

@Repository
interface DroneReactiveRepository : ReactiveCrudRepository<DroneEntity, UUID> {
    fun findByDroneName(name: String): Mono<DroneEntity>
    fun deleteByDroneId(id: UUID): Mono<Void>
}