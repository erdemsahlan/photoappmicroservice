package com.appsdeveloperblog.photoapp.api.users.photoappapiusers.ui.contollers;

import com.appsdeveloperblog.photoapp.api.users.photoappapiusers.Service.UsersService;
import com.appsdeveloperblog.photoapp.api.users.photoappapiusers.shared.UserDto;
import com.appsdeveloperblog.photoapp.api.users.photoappapiusers.ui.contollers.model.CreateUserRequestModel;
import com.appsdeveloperblog.photoapp.api.users.photoappapiusers.ui.contollers.model.CreateUserResponseModel;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserControllers {

        @Autowired
        UsersService usersService;
        @Autowired
        private final Environment env;

        public UserControllers(Environment env) {
                this.env = env;
        }

        @GetMapping("/status/check")
        public String status(){

                return "Working on port " + env.getProperty("local.server.port")+",with token="+env.getProperty("token.secret");
        }

        @PostMapping(value = "/create",
                        consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
                        produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
        public ResponseEntity<CreateUserResponseModel> createUser(@RequestBody CreateUserRequestModel userDetails){

                ModelMapper modelMapper= new ModelMapper();
                modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

                UserDto userDto=modelMapper.map(userDetails,UserDto.class);
                UserDto createdUser=usersService.createUser(userDto);

                CreateUserResponseModel returnValue=modelMapper.map(createdUser,CreateUserResponseModel.class);
                return  ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
        }

}
