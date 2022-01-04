package fr.myitworld.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

    // Detection Automatique
    @Bean
    DiscoveryClientRouteDefinitionLocator definitionLocator(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties properties) {
        // Lower case Service Id
        properties.setLowerCaseServiceId(true);
        
        return new DiscoveryClientRouteDefinitionLocator(rdc, properties);
    }

    /**
     // Detection Manuelle
     @Bean RouteLocator routeLocator(RouteLocatorBuilder builder) {
     return builder.routes()
     .route("r1", (r) -> r.path("/products/**").uri("lb://PRODUCT-SERVICE"))
     .route("r2", (r) -> r.path("/customers/**").uri("lb://CUSTOMER-SERVICE"))
     .build();
     }
     **/
}

