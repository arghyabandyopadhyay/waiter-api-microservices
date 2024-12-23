package com.businessgenie.apiGatewayService.config;

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
                .route("WaiterrUsersService", r -> r.path("/api/v1/users/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://WaiterrUsersService"))
                
                .route("WaiterrUserClientAllocationsService", r -> r.path("/api/v1/userclientallocations/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://WaiterrUserClientAllocationsService"))

                 .route("WaiterrOtpService", r -> r.path("/api/v1/otp/**")
                         .filters(f -> f.filter(filter))
                         .uri("lb://WaiterrOtpService"))

                 .route("WaiterrAuthenticationService", r -> r.path("/api/v1/authentication/**")
                         .filters(f -> f.filter(filter))
                         .uri("lb://WaiterrAuthenticationService"))
                .build();
    }

}
