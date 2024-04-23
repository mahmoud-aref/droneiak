package com.elmenus.domain.drone.statemachine

import com.elmenus.domain.drone.model.Drone
import com.elmenus.domain.drone.exception.InvalidDroneStateException
import com.elmenus.domain.drone.model.DroneState

class DroneStateMachine {

    fun nex(drone: Drone): Drone {
        return when (drone.state) {
            DroneState.CHARGED -> drone.setState(DroneState.IDLE)
            DroneState.IDLE -> drone.setState(DroneState.FLYING)
            DroneState.FLYING -> drone.setState(DroneState.DELIVERING)
            DroneState.DELIVERING -> drone.setState(DroneState.RETURNING)
            DroneState.RETURNING -> drone.setState(DroneState.IDLE)
            else -> throw InvalidDroneStateException()
        }
    }

    fun hyphenate(drone: Drone): Drone {
        return drone.setState(DroneState.HIBERNATING)
    }

    fun charge(drone: Drone): Drone {
        return drone.setState(DroneState.CHARGING)
    }

    fun fullCharge(drone: Drone): Drone {
        return drone.setState(DroneState.CHARGED)
    }

}