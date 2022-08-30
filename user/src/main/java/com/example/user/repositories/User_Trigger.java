package com.example.user.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value="usertrigger")
public interface User_Trigger extends JpaRepository<User_Trigger,Long> {
}
