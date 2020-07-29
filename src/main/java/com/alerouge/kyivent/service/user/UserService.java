package com.alerouge.kyivent.service.user;

 import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alerouge.kyivent.model.user.UserAuthoritiesEntity;
import com.alerouge.kyivent.model.user.UserEntity;
import com.alerouge.kyivent.repository.user.UserAuthoritiesRepository;
import com.alerouge.kyivent.repository.user.UserRepository;
import com.alerouge.kyivent.utility.UtiData;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email);
	} 

}