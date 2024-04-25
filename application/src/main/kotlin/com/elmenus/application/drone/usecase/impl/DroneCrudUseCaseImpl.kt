package com.elmenus.application.drone.usecase.impl

import com.elmenus.application.common.annotation.UseCase
import com.elmenus.application.drone.model.DroneDto
import com.elmenus.application.drone.model.DroneCreation
import com.elmenus.application.drone.usecase.DroneCrudUseCase
import com.elmenus.domain.drone.service.DroneService
import reactor.core.publisher.Mono

@UseCase
class DroneCrudUseCaseImpl (
    private val droneService: DroneService
) : DroneCrudUseCase {

    override fun createDrone(droneCreation: DroneCreation): Mono<DroneDto> {
        return Mono.fromFuture(
            droneService.createDrone(droneCreation.toDomainDrone())
        ).map { DroneDto(it) }
    }

}