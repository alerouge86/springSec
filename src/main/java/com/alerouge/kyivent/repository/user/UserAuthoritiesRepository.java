package com.alerouge.kyivent.repository.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alerouge.kyivent.model.user.UserAuthoritiesEntity;

@Repository
public interface UserAuthoritiesRepository extends CrudRepository<UserAuthoritiesEntity, Long> {

}
