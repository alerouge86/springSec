package com.alerouge.kyivent.service.user;

import com.alerouge.kyivent.model.VerificationToken;
import com.alerouge.kyivent.model.user.UserEntity;
import com.alerouge.kyivent.web.error.UserAlreadyExistException;

public interface IUserService {

    UserEntity findUserByEmail(String email);
}
