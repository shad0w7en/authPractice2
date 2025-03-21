package org.auth.repository;

import org.auth.model.UserInfoDto;
import org.springframework.data.repository.CrudRepository;

public interface UserInfoDtoRepository extends CrudRepository<UserInfoDto, Long> {
    UserInfoDto findByUsername(String username);
}
