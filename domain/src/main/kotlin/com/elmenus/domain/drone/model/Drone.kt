package com.elmenus.domain.drone.model

import com.elmenus.domain.order.model.Order
import java.util.UUID

data class Drone(
    val id: UUID,
    val name: String,
    val serialNumber: String,
    val model: String,
    val weight: Double,
    val maxSpeed: Double,
    val maxFlightTime: Int,
    val maxRange: Int,
    val batteryCapacity: Int,
    val state: DroneState,
    val currentCharge: Int,
    var droneOrder: DroneOrder?
) {
    fun setState(state: DroneState): Drone {
        return this.copy(state = state)
    }

    fun setCharge(charge: Int): Drone {
        return this.copy(currentCharge = charge)
    }

    fun setDroneOrder(droneOrder: DroneOrder): Drone {
        return this.copy(droneOrder = droneOrder)
    }
}

data class DroneOrder(
    val id: UUID,
    val drone: Drone,
    val order: Order
)

enum class DroneState {
    IDLE,
    FLYING,
    DELIVERING,
    RETURNING,
    HIBERNATING,
    CHARGING,
    CHARGED
}