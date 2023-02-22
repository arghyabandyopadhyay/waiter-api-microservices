package com.ArghyaBandyopadhyay.waiterrapi.apiGatewayService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHystrix
public class GatewayConfig {
    @Autowired
    AuthenticationFilter filter;
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("WaiterrApiCustomerService", r -> r.path("/api/v1/customers/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://WaiterrApiCustomerService"))

                 .route("WaiterrApiOtpService", r -> r.path("/api/v1/otp/**")
                         .filters(f -> f.filter(filter))
                         .uri("lb://WaiterrApiOtpService"))

                 .route("WaiterrApiAuthenticationService", r -> r.path("/api/v1/authentication/**")
                         .filters(f -> f.filter(filter))
                         .uri("lb://WaiterrApiAuthenticationService"))

                .route("WaiterrApiTransactionService", r -> r.path("/api/v1/payments/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://WaiterrApiTransactionService"))
                .build();
    }

}
