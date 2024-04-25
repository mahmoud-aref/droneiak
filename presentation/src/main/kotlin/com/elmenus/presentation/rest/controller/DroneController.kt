package com.elmenus.presentation.rest.controller

import com.elmenus.presentation.rest.facade.DroneFacade
import com.elmenus.presentation.rest.model.DroneCreationRequest
import com.elmenus.presentation.rest.model.DroneResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping(DroneController.DRONE_PATH)
class DroneController(
    private val droneFacade: DroneFacade
) {

    companion object {
        const val DRONE_PATH = "/api/v1/drones"
        const val DRONE_CREATE_PATH = "/create"
    }

    @PostMapping(DRONE_CREATE_PATH)
    fun createDrone(@RequestBody request: DroneCreationRequest): Mono<DroneResponse> {
        return droneFacade.createDrone(request)
    }

}