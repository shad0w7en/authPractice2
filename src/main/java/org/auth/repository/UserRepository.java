package org.auth.repository;

import org.auth.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserInfo , Long> {

    public UserInfo findByUsername(String username);
}
