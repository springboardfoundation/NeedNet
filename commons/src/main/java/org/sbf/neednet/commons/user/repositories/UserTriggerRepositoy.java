package org.sbf.neednet.commons.user.repositories;

import org.sbf.neednet.commons.user.entity.UserTrigger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTriggerRepositoy extends JpaRepository<UserTrigger,Long> {
}
