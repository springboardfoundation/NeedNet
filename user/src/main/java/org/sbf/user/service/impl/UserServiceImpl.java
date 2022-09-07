package org.sbf.user.service.impl;

import org.sbf.user.dto.UserDto;
import org.sbf.user.entity.User;
import org.sbf.user.repositories.UserRepositoy;
import org.sbf.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserRepositoy userRepositoy;
    private User user;

    @Override
    public UserDto saveUser(UserDto userDto) {
     //copy all data into new user object pass that to save
        User o=new User();
        o.setId(user.getId());
        o.setUserNumber(user.getUserNumber());
        o.setName(user.getName());
        o.setEmail(user.getEmail());
        o.setPlace(user.getPlace());
        o.setAge(user.getAge());
        o.setGender(user.getGender());
        o.setOccupation(user.getOccupation());
        o.setMobileNumber(user.getMobileNumber());
        userRepositoy.save(o);
      //before return copy all data into userdto and return
        UserDto dto= new UserDto();
        userDto.setUserNumber(o.getUserNumber());
        userDto.setAge(o.getAge());
        userDto.setEmail(o.getEmail());
        userDto.setGender(o.getGender());
        userDto.setName(o.getName());
        userDto.setPlace(o.getPlace());
        userDto.setOccupation(o.getOccupation());
        userDto.setMobileNumber(o.getMobileNumber());
        return userDto;
    }
}
