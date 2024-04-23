package com.elmenus.infrastructure.datasource.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@Configuration
@EnableReactiveMongoRepositories(
    basePackages = ["com.elmenus.infrastructure"]
)
class DatabaseConfigs