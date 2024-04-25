package com.elmenus.domain.drone.service

import com.elmenus.domain.drone.model.Drone
import com.elmenus.domain.order.model.Order
import java.util.Optional
import java.util.UUID
import java.util.concurrent.CompletableFuture

interface DroneService {
    fun getDroneById(id: UUID): CompletableFuture<Drone>
    fun getAllDrones(): CompletableFuture<List<Drone>>
    fun createDrone(drone: Drone): CompletableFuture<Drone>
    fun updateDrone(drone: Drone): CompletableFuture<Drone>
    fun deleteDrone(id: UUID): CompletableFuture<Boolean>
    fun chargeDrone(id: UUID, amount: Int): CompletableFuture<Optional<Drone>>
    fun loadDrone(id: UUID, order: Order): CompletableFuture<Drone>
}