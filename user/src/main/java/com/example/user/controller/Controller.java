package com.example.user.controller;

import com.example.user.entity.signup;
import com.example.user.service.tableservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private tableservice table;
    @RequestMapping(method= RequestMethod.POST,value="/add")
    public String saveinfo(@RequestBody signup SignUp)
    {
        if(table.addinfo(SignUp)!=null)
        {
            return "data inserted Successfully";
        }
        else {
            return "error";
        }
    }
}
