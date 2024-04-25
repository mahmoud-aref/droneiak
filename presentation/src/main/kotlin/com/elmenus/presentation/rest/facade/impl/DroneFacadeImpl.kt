package com.elmenus.presentation.rest.facade.impl

import com.elmenus.application.drone.usecase.DroneCrudUseCase
import com.elmenus.presentation.rest.facade.DroneFacade
import com.elmenus.presentation.rest.model.DroneCreationRequest
import com.elmenus.presentation.rest.model.DroneResponse
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class DroneFacadeImpl(
    private val droneUseCase: DroneCrudUseCase
) : DroneFacade {
    override fun createDrone(request: DroneCreationRequest): Mono<DroneResponse> {
        return droneUseCase.createDrone(request.droneCreation)
            .map { DroneResponse(it) }
    }
}