package org.sbf.user.controller;


import org.sbf.user.dto.UserDto;
import org.sbf.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/user/register")
@RestController
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserDto saveinfo(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }
}
