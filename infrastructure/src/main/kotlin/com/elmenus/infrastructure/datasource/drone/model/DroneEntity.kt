package com.elmenus.infrastructure.datasource.drone.model

import com.elmenus.domain.drone.model.Drone
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document(collection = "drones")
data class DroneEntity(
    val drone: Drone
) {
    @Id
    val id: UUID = drone.id
    fun Drone.toEntity(): DroneEntity = DroneEntity(this)
}
