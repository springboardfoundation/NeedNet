package org.sbf.neednet.commons.user.repositories;

import org.sbf.neednet.commons.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoy extends JpaRepository<User, Long> {
}
