package com.appsdeveloperblog.photoapp.api.users.photoappapiusers.Service;

import com.appsdeveloperblog.photoapp.api.users.photoappapiusers.data.AlbumsServiceClient;
import com.appsdeveloperblog.photoapp.api.users.photoappapiusers.data.UserEntity;
import com.appsdeveloperblog.photoapp.api.users.photoappapiusers.data.UsersRepository;
import com.appsdeveloperblog.photoapp.api.users.photoappapiusers.shared.UserDto;
import com.appsdeveloperblog.photoapp.api.users.photoappapiusers.ui.contollers.model.AlbumResponseModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UsersService{

    UsersRepository usersRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    AlbumsServiceClient albumsServiceClient;

    //RestTemplate restTemplate;

    @Autowired
    public UserServiceImpl(
            UsersRepository usersRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            RestTemplate restTemplate){

        this.usersRepository=usersRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.albumsServiceClient=albumsServiceClient;
       //this.restTemplate=restTemplate;
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

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        UserEntity userEntity = usersRepository.findByEmail(email);
        if(userEntity == null) throw new UsernameNotFoundException(email);


        return new ModelMapper().map(userEntity,UserDto.class);
    }

    @Override
    public UserDto getUserByUserId(String userId) {

        UserEntity userEntity=usersRepository.findByUserId(userId);
        if(userId==null) throw new UsernameNotFoundException("user not found");

        UserDto userDto=new ModelMapper().map(userEntity,UserDto.class);
        /*
        String albumsUrl=String.format("http://ALBUMS-WS:3377/users/%s/albums",userId);
        ResponseEntity<List<AlbumResponseModel>> albumsListResponse = restTemplate.exchange(albumsUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<AlbumResponseModel>>() {
        });
        List<AlbumResponseModel> albumsList = albumsListResponse.getBody();

        */

        List<AlbumResponseModel> albumsList = albumsServiceClient.getAlbums(userId);
        userDto.setAlbums(albumsList);
        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = usersRepository.findByEmail(username);

        if(userEntity == null) throw new UsernameNotFoundException(username);

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
    }

}
