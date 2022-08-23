package com.appsdeveloperblog.photoapp.api.users.photoappapiusers.ui.contollers;

import com.appsdeveloperblog.photoapp.api.users.photoappapiusers.shared.UserDto;
import com.appsdeveloperblog.photoapp.api.users.photoappapiusers.ui.models.model;
import com.appsdeveloperblog.photoapp.api.users.photoappapiusers.users.service.UsersService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserControllers {

        @Autowired
         UsersService usersService;
        @Autowired
        private Environment env;
        @GetMapping("/status/check")
        public String status(){
                return "Working on port " + env.getProperty("local.server.port");
        }

        @PostMapping
        public String createUser(@Valid @RequestBody model userDetails){

                ModelMapper modelMapper = new ModelMapper();
                modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

                UserDto userDto =modelMapper.map(userDetails,UserDto.class);
                usersService.createUser(userDto);

                return "";
        }

}
