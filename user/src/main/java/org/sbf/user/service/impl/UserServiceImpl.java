package org.sbf.user.service.impl;

import org.sbf.user.entity.User;
import org.sbf.user.repositories.UserRepositoy;
import org.sbf.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserRepositoy userRepositoy;

    @Override
    public User saveUser(User SignUp) {
        return userRepositoy.save(SignUp);
    }
}
