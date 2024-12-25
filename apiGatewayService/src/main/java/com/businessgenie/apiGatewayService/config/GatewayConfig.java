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

                .route("WaiterrMaxTakeAwayService", r -> r.path("/api/v1/maxtakeaway/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://WaiterrMaxTakeAwayService"))

                .route("WaiterrClientService", r -> r.path("/api/v1/client/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://WaiterrClientService"))
                        
                .route("WaiterrMenuGroupService", r -> r.path("/api/v1/menugroup/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://WaiterrMenuGroupService"))

                .route("WaiterrMenuItemService", r -> r.path("/api/v1/menu/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://WaiterrMenuItemService"))

                .route("WaiterrOrderService", r -> r.path("/api/v1/orders/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://WaiterrOrderService"))

                .route("WaiterrOutletService", r -> r.path("/api/v1/outlets/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://WaiterrOutletService"))

                .route("WaiterrRunningOrderService", r -> r.path("/api/v1/runningorders/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://WaiterrRunningOrderService"))

                .route("WaiterrTaxClassService", r -> r.path("/api/v1/taxclass/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://WaiterrTaxClassService"))
                .build();
    }

}
