package com.elmenus.application.drone.config

import com.elmenus.application.drone.repository.DroneDao
import com.elmenus.domain.drone.repository.DroneRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DroneConfig {

    @Bean
    fun createDroneRepository(): DroneRepository {
        return DroneDao()
    }


}