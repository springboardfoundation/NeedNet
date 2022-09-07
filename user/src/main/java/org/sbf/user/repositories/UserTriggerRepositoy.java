package org.sbf.user.repositories;

import org.sbf.user.entity.UserTrigger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTriggerRepositoy extends JpaRepository<UserTrigger,Long> {
}
