package com.elmenus.presentation.rest.facade

import com.elmenus.presentation.rest.model.DroneCreationRequest
import com.elmenus.presentation.rest.model.DroneResponse
import reactor.core.publisher.Mono

interface DroneFacade {
    fun createDrone(request: DroneCreationRequest): Mono<DroneResponse>
}