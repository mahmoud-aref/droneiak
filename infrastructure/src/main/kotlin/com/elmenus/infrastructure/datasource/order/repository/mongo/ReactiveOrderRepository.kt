package com.elmenus.infrastructure.datasource.order.repository.mongo

import com.elmenus.domain.order.model.OrderState
import com.elmenus.infrastructure.datasource.order.model.OrderEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import java.util.UUID

interface ReactiveOrderRepository : ReactiveCrudRepository<OrderEntity, UUID> {
    fun findByOrderState(state: OrderState): Flux<OrderEntity>
    fun findByOrderUserId(userId: UUID): Flux<OrderEntity>
}