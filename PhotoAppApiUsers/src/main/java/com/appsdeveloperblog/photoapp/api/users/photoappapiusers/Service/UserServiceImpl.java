package com.appsdeveloperblog.photoapp.api.users.photoappapiusers.Service;

import com.appsdeveloperblog.photoapp.api.users.photoappapiusers.data.UserEntity;
import com.appsdeveloperblog.photoapp.api.users.photoappapiusers.data.UsersRepository;
import com.appsdeveloperblog.photoapp.api.users.photoappapiusers.shared.UserDto;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
public class UserServiceImpl implements UsersService{

    UsersRepository usersRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(
            UsersRepository usersRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder){

        this.usersRepository=usersRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }


    @Override
    public UserDto createUser(UserDto userDetails) {

        userDetails.setUserId((UUID.randomUUID().toString()));
        userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
        ModelMapper modelMapper= new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);


        UserEntity userEntity=modelMapper.map(userDetails,UserEntity.class );

        usersRepository.save(userEntity);

        UserDto returnValue=modelMapper.map(userEntity,UserDto.class);

        return returnValue;
    }

}
