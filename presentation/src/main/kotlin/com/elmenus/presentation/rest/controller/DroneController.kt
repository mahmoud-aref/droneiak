package com.elmenus.presentation.rest.controller

import com.elmenus.application.drone.usecase.DroneCrudUseCase
import com.elmenus.presentation.rest.model.DroneCreationRequest
import com.elmenus.presentation.rest.model.DroneResponse
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping(DroneController.DRONE_PATH)
class DroneController(
    private val droneUseCase: DroneCrudUseCase
) {

    companion object {
        const val DRONE_PATH = "/api/v1/drones"
        const val DRONE_CREATE_PATH = "/create"
    }

    @PostMapping(DRONE_CREATE_PATH)
    fun createDrone(@RequestBody request: DroneCreationRequest): Mono<DroneResponse> {
        return droneUseCase.createDrone(request.droneCreation)
            .map { DroneResponse(it) }
    }


}