package com.elmenus.application.drone.config

import com.elmenus.application.drone.repository.DroneDao
import com.elmenus.domain.drone.repository.DroneRepository
import com.elmenus.domain.drone.service.DroneService
import com.elmenus.domain.drone.service.impl.DroneServiceImpl
import com.elmenus.domain.drone.statemachine.DroneStateMachine
import com.elmenus.infrastructure.datasource.drone.repository.mongo.ReactiveDroneRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DroneConfig(
    private val reactiveDroneRepository: ReactiveDroneRepository
) {

    @Bean
    fun getDroneRepository(): DroneRepository {
        return DroneDao(reactiveDroneRepository)
    }


    @Bean
    fun getStateMachine(): DroneStateMachine {
        return DroneStateMachine()
    }

    @Bean
    fun createDroneService(): DroneService {
        return DroneServiceImpl(getStateMachine(), getDroneRepository())
    }

}