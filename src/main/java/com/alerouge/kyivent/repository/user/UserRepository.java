package com.alerouge.kyivent.repository.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alerouge.kyivent.model.user.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
	
    UserEntity findByEmail(String email);


}
