package com.elmenus.domain.drone.repository

import com.elmenus.domain.drone.model.Drone
import java.util.Optional
import java.util.UUID
import java.util.concurrent.CompletableFuture

interface DroneRepository {
    fun findById(id: UUID): CompletableFuture<Optional<Drone>>
    fun findAll(): CompletableFuture<List<Drone>>
    fun findByName(name: String): CompletableFuture<Optional<Drone>>
    fun save(drone: Drone): CompletableFuture<Optional<Drone>>
    fun delete(drone: Drone): CompletableFuture<Boolean>
}