package com.elmenus.application.product.config

import com.elmenus.application.product.repository.ProductDao
import com.elmenus.domain.product.service.ProductService
import com.elmenus.domain.product.service.impl.ProductServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProductConfig(
    private val productDao: ProductDao
) {
    @Bean
    fun productService(): ProductService {
        return ProductServiceImpl(productDao)
    }
}