package com.appsdeveloperblog.photoapp.api.users.photoappapiusers.ui.contollers;

import com.appsdeveloperblog.photoapp.api.users.photoappapiusers.Service.UsersService;
import com.appsdeveloperblog.photoapp.api.users.photoappapiusers.shared.UserDto;
import com.appsdeveloperblog.photoapp.api.users.photoappapiusers.ui.contollers.model.CreateUserRequestModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserControllers {

        UsersService usersService;
        private final Environment env;

        public UserControllers(Environment env) {
                this.env = env;
        }

        @GetMapping("/status/check")
        public String status(){
                return "Working on port " + env.getProperty("local.server.port");
        }

        @PostMapping("/create")
        public String createUser(@RequestBody CreateUserRequestModel userDetails){

                ModelMapper modelMapper= new ModelMapper();
                modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

                UserDto userDto=modelMapper.map(userDetails,UserDto.class);
                usersService.createUser(userDto);
                int i=0;i++;
                return "";
        }

}
