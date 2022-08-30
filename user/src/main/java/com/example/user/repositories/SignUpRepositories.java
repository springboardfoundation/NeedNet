package com.example.user.repositories;

import com.example.user.entity.signup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value="signuprespositories")
public interface SignUpRepositories extends JpaRepository<signup,Long> {
}
