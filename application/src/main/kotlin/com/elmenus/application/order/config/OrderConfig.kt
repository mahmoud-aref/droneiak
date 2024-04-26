package com.elmenus.application.order.config

import com.elmenus.application.order.repository.OrderDao
import com.elmenus.domain.order.repository.OrderRepository
import com.elmenus.domain.order.service.OrderService
import com.elmenus.domain.order.service.impl.OrderServiceImpl
import com.elmenus.domain.order.statemachine.OrderStateMachine
import com.elmenus.infrastructure.datasource.mongo.order.repository.ReactiveOrderRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OrderConfig(
    private val orderDao: OrderDao
) {

    @Bean
    fun orderStateMachine(): OrderStateMachine {
        return OrderStateMachine()
    }

    @Bean
    fun orderService(): OrderService {
        return OrderServiceImpl(orderDao, orderStateMachine())
    }

}