package com.elmenus.application.drone.config

import com.elmenus.application.drone.repository.DroneDao
import com.elmenus.domain.drone.repository.DroneRepository
import com.elmenus.domain.drone.service.DroneService
import com.elmenus.domain.drone.service.impl.DroneServiceImpl
import com.elmenus.domain.drone.statemachine.DroneStateMachine
import com.elmenus.infrastructure.datasource.mongo.drone.repository.ReactiveDroneRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DroneConfig(
    private val droneDao: DroneDao
) {

    @Bean
    fun getStateMachine(): DroneStateMachine {
        return DroneStateMachine()
    }

    @Bean
    fun createDroneService(): DroneService {
        return DroneServiceImpl(getStateMachine(), droneDao)
    }

}