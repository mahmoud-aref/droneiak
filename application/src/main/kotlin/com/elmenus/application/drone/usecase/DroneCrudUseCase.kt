package com.elmenus.application.drone.usecase

import com.elmenus.application.drone.model.DroneDto
import com.elmenus.application.drone.model.DroneCreation
import reactor.core.publisher.Mono
import java.util.UUID

interface DroneCrudUseCase {
    fun createDrone(droneCreation: DroneCreation): Mono<DroneDto>
    fun getDrone(droneId: UUID): Mono<DroneDto>
}