package com.elmenus.domain.order.service

import com.elmenus.domain.drone.model.Drone
import com.elmenus.domain.order.model.Order
import com.elmenus.domain.order.model.OrderItems
import com.elmenus.domain.order.model.OrderState
import java.util.UUID
import java.util.concurrent.CompletableFuture

interface OrderService {
    fun placeOrder(order: Order): CompletableFuture<Order>
    fun changeOderState(orderId: UUID): CompletableFuture<Order>
    fun cancelOrder(orderId: UUID): CompletableFuture<Order>
    fun rejectOrder(orderId: UUID): CompletableFuture<Order>
    fun getOrder(orderId: UUID): CompletableFuture<Order>
    fun getOrders(): CompletableFuture<List<Order>>
    fun getOrdersByState(state: OrderState): CompletableFuture<List<Order>>
    fun getAllOrders(): CompletableFuture<List<Order>>
    fun getAllOrdersByState(state: OrderState): CompletableFuture<List<Order>>
    fun getAllOrdersByUser(userId: UUID): CompletableFuture<List<Order>>
    fun updateOrderItems(orderId: UUID, items: List<OrderItems>): CompletableFuture<Order>
}