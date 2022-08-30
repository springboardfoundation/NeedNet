package com.example.user.service.impl;

import com.example.user.entity.signup;
import com.example.user.repositories.SignUpRepositories;
import com.example.user.service.tableservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class tableserviceimp implements tableservice {

    @Autowired
    private SignUpRepositories signUpRepositories;

    @Override
    public signup addinfo(signup SignUp) {
        return signUpRepositories.save(SignUp);
    }
}
