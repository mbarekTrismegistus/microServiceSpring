package com.example.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StaticRoutesConfiguration {
    @Bean
    RouteLocator staticRoutes(RouteLocatorBuilder routes) {
        return routes.routes()
                .route("customers", route -> route.path("/customers/**").uri("lb://CUSTOMER-SERVICE"))
                .route("products", route -> route.path("/products/**").uri("lb://INVENTORY-SERVICE"))
                .route("bills", route -> route.path("/bills/**").uri("lb://BILLING-SERVICE"))
                .build();
    }
}
