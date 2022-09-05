package org.sbf.user.controller;
import org.sbf.user.entity.User;
import org.sbf.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/user/register")
@RestController
public class UserRegistrationController  {

    @Autowired
    private UserService userService;
    @PostMapping
    public String saveinfo(@RequestBody User user)
    {
        if(userService.saveUser(user)!=null)
        {
            return "data inserted Successfully";
        }
        else {
            return "error";
        }
    }
}
