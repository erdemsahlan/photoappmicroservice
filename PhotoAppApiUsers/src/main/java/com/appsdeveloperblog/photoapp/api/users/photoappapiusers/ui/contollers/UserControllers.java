package com.appsdeveloperblog.photoapp.api.users.photoappapiusers.ui.contollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserControllers {

        @GetMapping("/status/check")
        public String status(){
            return "service work";
        }

}
