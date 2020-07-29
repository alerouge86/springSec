package com.alerouge.kyivent.service.user;

import com.alerouge.kyivent.model.user.UserEntity;

public interface IUserService {

    UserEntity findUserByEmail(String email);
}
