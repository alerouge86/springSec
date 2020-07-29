package com.alerouge.kyivent.service.user;

import com.alerouge.kyivent.model.VerificationToken;
import com.alerouge.kyivent.model.user.UserDto;
import com.alerouge.kyivent.model.user.UserEntity;
import com.alerouge.kyivent.web.error.UserAlreadyExistException;

public interface IUserService {

    UserEntity registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException;

    void createVerificationTokenForUser(UserEntity user, String token);

    void saveRegisteredUser(UserEntity user);

    VerificationToken getVerificationToken(String VerificationToken);

    UserEntity findUserByEmail(String email);
}
