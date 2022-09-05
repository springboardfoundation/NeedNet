package org.sbf.user.controller;
import org.sbf.user.entity.User;
import org.sbf.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegistrationController  {

    @Autowired
    private UserService table;
    @RequestMapping(method= RequestMethod.POST,value="/api/v1/user/register")
    public String saveinfo(@RequestBody User SignUp)
    {
        if(table.saveUser(SignUp)!=null)
        {
            return "data inserted Successfully";
        }
        else {
            return "error";
        }
    }
}
