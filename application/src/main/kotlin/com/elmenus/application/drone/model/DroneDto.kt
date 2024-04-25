package com.elmenus.application.drone.model

import com.elmenus.domain.drone.model.Drone
import com.elmenus.domain.drone.model.DroneState
import java.util.*

data class DroneDto(
    val drone: Drone
)

data class DroneCreation(
    val name: String,
    val serialNumber: String,
    val model: String,
    val weight: Double,
    val maxWeight: Double,
    val maxSpeed: Double,
    val maxFlightTime: Int,
    val maxRange: Int,
    val batteryCapacity: Int
) {
    fun toDomainDrone(): Drone {
        return Drone(
            id = UUID.randomUUID(),
            name = name,
            serialNumber = serialNumber,
            model = model,
            weight = weight,
            maxSpeed = maxSpeed,
            maxFlightTime = maxFlightTime,
            maxRange = maxRange,
            batteryCapacity = batteryCapacity,
            DroneState.IDLE,
            100,
            null
        )
    }
}



