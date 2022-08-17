package com.appsdeveloperblog.photoapp.gatewayserver.photoappgatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PhotoAppGatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotoAppGatewayServerApplication.class, args);
    }



}
