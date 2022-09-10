package com.appsdeveloperblog.photoapp.gatewayserver.photoappgatewayserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

@Configuration
public class GlobalFiltersConfiguration {

    final Logger logger= LoggerFactory.getLogger(GlobalFiltersConfiguration.class);

    @Order(1)
    @Bean
    public GlobalFilter secondPreFilter(){

        logger.info("second global pre-filter is executed");
        return (exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                logger.info("third post-filter is executed");
            }));
        };
    }

    @Order(2)
    @Bean
    public GlobalFilter thirdPreFilter(){

        logger.info("third global pre-filter is executed");
        return (exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                logger.info("second  post-filter is executed");
            }));
        };
    }

    @Order(3)
    @Bean
    public GlobalFilter fourthPreFilter(){

        logger.info("fourth global pre-filter is executed");
        return (exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                logger.info("first post-filter is executed");
            }));
        };
    }

}
