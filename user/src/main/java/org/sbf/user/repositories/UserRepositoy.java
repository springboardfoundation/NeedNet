package org.sbf.user.repositories;

import org.sbf.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  UserRepositoy extends JpaRepository<User,Long> {
}
