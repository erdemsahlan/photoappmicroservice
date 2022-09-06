package com.appsdeveloperblog.photoapp.api.users.photoappapiusers.Service;

import com.appsdeveloperblog.photoapp.api.users.photoappapiusers.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UsersService extends UserDetailsService {


      UserDto createUser(UserDto userDetails);
      UserDto getUserDetailsByEmail(String email);

}
