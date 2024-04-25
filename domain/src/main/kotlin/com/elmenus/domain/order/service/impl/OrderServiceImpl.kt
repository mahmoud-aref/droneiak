package com.elmenus.domain.order.service.impl

import com.elmenus.domain.order.exception.OrderNotFoundException
import com.elmenus.domain.order.model.Order
import com.elmenus.domain.order.model.OrderItems
import com.elmenus.domain.order.model.OrderState
import com.elmenus.domain.order.repository.OrderRepository
import com.elmenus.domain.order.service.OrderService
import com.elmenus.domain.order.statemachine.OrderStateMachine
import java.util.*
import java.util.concurrent.CompletableFuture

class OrderServiceImpl(
    private val orderRepository: OrderRepository,
    private val orderStateMachine: OrderStateMachine
) : OrderService {

    override fun placeOrder(order: Order): CompletableFuture<Order> {
        return orderRepository.saveOrder(order)
    }

    override fun changeOderState(orderId: UUID): CompletableFuture<Order> {
        return orderRepository.findOrderById(orderId)
            .thenApply { order ->
                order.orElseThrow { OrderNotFoundException() }
            }
            .thenApply { order ->
                orderStateMachine.next(order)
            }
            .thenCompose { order ->
                orderRepository.updateOrder(order)
            }
    }

    override fun cancelOrder(orderId: UUID): CompletableFuture<Order> {
        return orderRepository.findOrderById(orderId)
            .thenApply { order ->
                order.orElseThrow { OrderNotFoundException() }
            }
            .thenApply { order ->
                orderStateMachine.cancel(order)
            }
            .thenCompose { order ->
                orderRepository.updateOrder(order)
            }
    }

    override fun rejectOrder(orderId: UUID): CompletableFuture<Order> {
        return orderRepository.findOrderById(orderId)
            .thenApply { order ->
                order.orElseThrow { OrderNotFoundException() }
            }
            .thenApply { order ->
                orderStateMachine.reject(order)
            }
            .thenCompose { order ->
                orderRepository.updateOrder(order)
            }
    }

    override fun getOrder(orderId: UUID): CompletableFuture<Order> {
        return orderRepository.findOrderById(orderId)
            .thenApply { order ->
                order.orElseThrow { OrderNotFoundException() }
            }
    }

    override fun getOrders(): CompletableFuture<List<Order>> {
        return orderRepository.findAllOrders()
            .thenApply { orders ->
                orders.orElse(emptyList())
            }
    }

    override fun getOrdersByState(state: OrderState): CompletableFuture<List<Order>> {
        return orderRepository.findAllOrders()
            .thenApply { orders ->
                orders.orElse(emptyList())
                    .filter { it.state == state }
            }
    }

    override fun getAllOrders(): CompletableFuture<List<Order>> {
        return orderRepository.findAllOrders()
            .thenApply { orders ->
                orders.orElse(emptyList())
            }
    }

    override fun getAllOrdersByState(state: OrderState): CompletableFuture<List<Order>> {
        return orderRepository.findAllOrdersByState(state.name)
            .thenApply { orders ->
                orders.orElse(emptyList())
            }
    }

    override fun getAllOrdersByUser(userId: UUID): CompletableFuture<List<Order>> {
        return orderRepository.findAllOrdersByUser(userId)
            .thenApply { orders ->
                orders.orElse(emptyList())
            }
    }

    override fun updateOrderItems(orderId: UUID, items: List<OrderItems>): CompletableFuture<Order> {
        return orderRepository.findOrderById(orderId)
            .thenApply { order ->
                order.orElseThrow { OrderNotFoundException() }
            }
            .thenApply { order ->
                order.updateItems(items)
            }
            .thenCompose { order ->
                orderRepository.updateOrder(order)
            }
    }

}