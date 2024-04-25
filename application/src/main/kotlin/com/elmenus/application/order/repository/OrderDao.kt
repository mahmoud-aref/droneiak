package com.elmenus.application.order.repository

import com.elmenus.domain.order.model.Order
import com.elmenus.domain.order.model.OrderState
import com.elmenus.domain.order.repository.OrderRepository
import com.elmenus.infrastructure.datasource.order.model.OrderEntity
import com.elmenus.infrastructure.datasource.order.repository.mongo.ReactiveOrderRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.*
import java.util.concurrent.CompletableFuture

@Repository
class OrderDao(
    private val reactiveOrderRepository: ReactiveOrderRepository
) : OrderRepository {

    override fun saveOrder(order: Order): CompletableFuture<Order> {
        return reactiveOrderRepository.save(OrderEntity(order))
            .map { it.order }
            .toFuture()
    }

    override fun findOrderById(orderId: UUID): CompletableFuture<Optional<Order>> {
        return reactiveOrderRepository.findById(orderId)
            .map { Optional.of(it.order) }
            .defaultIfEmpty(Optional.empty())
            .toFuture()
    }

    override fun deleteOrder(orderId: UUID): CompletableFuture<Boolean> {
        return reactiveOrderRepository.deleteById(orderId)
            .then(Mono.fromCallable { true })
            .toFuture()
    }

    override fun updateOrder(order: Order): CompletableFuture<Order> {
        return reactiveOrderRepository.save(OrderEntity(order))
            .map { it.order }
            .toFuture()
    }

    override fun findAllOrders(): CompletableFuture<Optional<List<Order>>> {
        return reactiveOrderRepository.findAll()
            .map { it.order }
            .collectList()
            .map { Optional.of(it) }
            .defaultIfEmpty(Optional.empty())
            .toFuture()
    }

    override fun findAllOrdersByState(state: String): CompletableFuture<Optional<List<Order>>> {
        return reactiveOrderRepository.findByOrderState(OrderState.valueOf(state))
            .map { it.order }
            .collectList()
            .map { Optional.of(it) }
            .defaultIfEmpty(Optional.empty())
            .toFuture()
    }

    override fun findAllOrdersByUser(userId: UUID): CompletableFuture<Optional<List<Order>>> {
        return reactiveOrderRepository.findByOrderUserId(userId)
            .map { it.order }
            .collectList()
            .map { Optional.of(it) }
            .defaultIfEmpty(Optional.empty())
            .toFuture()
    }

}