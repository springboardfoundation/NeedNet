package org.sbf.user.service;

import org.sbf.user.dto.UserDto;
import org.sbf.user.entity.User;

public interface UserService {

    UserDto saveUser(UserDto userDto);
}
