package com.elmenus.application.drone.repository

import com.elmenus.domain.drone.model.Drone
import com.elmenus.domain.drone.repository.DroneRepository
import com.elmenus.infrastructure.datasource.drone.model.DroneEntity
import com.elmenus.infrastructure.datasource.drone.repository.mongo.ReactiveDroneRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.Optional
import java.util.UUID
import java.util.concurrent.CompletableFuture

@Repository
class DroneDao(
    private val reactiveDroneRepository: ReactiveDroneRepository
) : DroneRepository {

    override fun findById(id: UUID): CompletableFuture<Optional<Drone>> {
        return reactiveDroneRepository.findById(id)
            .map { Optional.of(it.drone) }
            .defaultIfEmpty(Optional.empty())
            .toFuture()
    }

    override fun findAll(): CompletableFuture<List<Drone>> {
        return reactiveDroneRepository.findAll()
            .map { it.drone }
            .collectList()
            .toFuture()
    }

    override fun findByName(name: String): CompletableFuture<Optional<Drone>> {
        return reactiveDroneRepository.findByDroneName(name)
            .map { Optional.of(it.drone) }
            .defaultIfEmpty(Optional.empty())
            .toFuture()
    }

    override fun save(drone: Drone): CompletableFuture<Optional<Drone>> {
        return reactiveDroneRepository.save(DroneEntity(drone))
            .map { Optional.of(it.drone) }
            .defaultIfEmpty(Optional.empty())
            .toFuture()
    }

    override fun delete(drone: Drone): CompletableFuture<Boolean> {
        return reactiveDroneRepository.deleteByDroneId(drone.id)
            .then(Mono.fromCallable { true })
            .toFuture()
    }

}