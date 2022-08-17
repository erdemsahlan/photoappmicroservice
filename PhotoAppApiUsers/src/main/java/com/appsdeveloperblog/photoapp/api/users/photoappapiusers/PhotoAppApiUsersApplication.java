package com.appsdeveloperblog.photoapp.api.users.photoappapiusers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PhotoAppApiUsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotoAppApiUsersApplication.class, args);
    }

}
