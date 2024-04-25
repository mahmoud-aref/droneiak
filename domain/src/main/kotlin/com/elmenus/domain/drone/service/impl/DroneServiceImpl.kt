package com.elmenus.domain.drone.service.impl

import com.elmenus.domain.drone.exception.DroneNotFoundException
import com.elmenus.domain.drone.model.Drone
import com.elmenus.domain.drone.model.DroneOrder
import com.elmenus.domain.drone.model.DroneState
import com.elmenus.domain.drone.repository.DroneRepository
import com.elmenus.domain.drone.service.DroneService
import com.elmenus.domain.drone.statemachine.DroneStateMachine
import com.elmenus.domain.order.model.Order
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.CompletableFuture

@Service
class DroneServiceImpl(
    private val droneStateMachine: DroneStateMachine,
    private val droneRepository: DroneRepository
) : DroneService {


    override fun getDroneById(id: UUID): CompletableFuture<Drone> {
        return droneRepository.findById(id)
            .thenApplyAsync { droneOptional ->
                droneOptional.orElseThrow { DroneNotFoundException() }
            }
    }

    override fun getAllDrones(): CompletableFuture<List<Drone>> {
        return droneRepository.findAll()
            .thenApplyAsync {
                it
            }
    }

    override fun createDrone(drone: Drone): CompletableFuture<Drone> {
        return droneRepository.save(drone)
            .thenApplyAsync { droneOptional ->
                droneOptional.orElseThrow { DroneNotFoundException() }
            }
    }

    override fun updateDrone(drone: Drone): CompletableFuture<Drone> {
        return droneRepository.save(drone)
            .thenApplyAsync { droneOptional ->
                droneOptional.orElseThrow { DroneNotFoundException() }
            }
    }

    override fun deleteDrone(id: UUID): CompletableFuture<Boolean> {
        return droneRepository.findById(id)
            .thenApplyAsync { droneOptional ->
                droneOptional.orElseThrow { DroneNotFoundException() }
            }
            .thenComposeAsync { drone ->
                droneRepository.delete(drone)
            }
    }

    override fun chargeDrone(id: UUID, amount: Int): CompletableFuture<Optional<Drone>> {
        return getDroneById(id)
            .thenApplyAsync { drone ->
                drone.setCharge(amount)
            }
            .thenComposeAsync { drone ->
                droneRepository.save(drone)
            }
    }

    override fun loadDrone(id: UUID, order: Order): CompletableFuture<Drone> {
        return getDroneById(id)
            .thenApplyAsync { drone ->
                check(DroneState.IDLE != drone.state) {
                    throw IllegalStateException("Drone is not in IDLE state")
                }
                val droneOrder = DroneOrder(UUID.randomUUID(), drone.id, order)
                droneStateMachine.nex(drone)
                drone.setDroneOrder(droneOrder)
            }.thenComposeAsync { drone ->
                droneRepository.save(drone)
                    .thenApplyAsync { droneOptional ->
                        droneOptional.orElseThrow { DroneNotFoundException() }
                    }
            }
    }
}