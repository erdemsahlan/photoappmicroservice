package com.appsdeveloperblog.photoapp.api.users.photoappapiusers.Service;

import com.appsdeveloperblog.photoapp.api.users.photoappapiusers.data.UserEntity;
import com.appsdeveloperblog.photoapp.api.users.photoappapiusers.data.UsersRepository;
import com.appsdeveloperblog.photoapp.api.users.photoappapiusers.shared.UserDto;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
public class UserServiceImpl implements UsersService{

    UsersRepository usersRepository;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository){
        this.usersRepository=usersRepository;
    }



    @Override
    public UserDto createUser(UserDto userDetails) {

        userDetails.setUserId((UUID.randomUUID().toString()));
        ModelMapper modelMapper= new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);


        UserEntity userEntity=modelMapper.map(userDetails,UserEntity.class );
        userEntity.setEncryptedPassword("test");

        usersRepository.save(userEntity);

        return null;
    }
}
