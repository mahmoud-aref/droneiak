package com.elmenus.domain.order.repository

import com.elmenus.domain.order.model.Order
import java.util.Optional
import java.util.UUID
import java.util.concurrent.CompletableFuture

interface OrderRepository {
    fun saveOrder(order: Order): CompletableFuture<Order>
    fun findOrderById(orderId: UUID): CompletableFuture<Optional<Order>>
    fun deleteOrder(orderId: UUID): CompletableFuture<Boolean>
    fun updateOrder(order: Order): CompletableFuture<Order>
    fun findAllOrders(): CompletableFuture<Optional<List<Order>>>
    fun findAllOrdersByState(state: String): CompletableFuture<Optional<List<Order>>>
    fun findAllOrdersByUser(userId: UUID): CompletableFuture<Optional<List<Order>>>
}