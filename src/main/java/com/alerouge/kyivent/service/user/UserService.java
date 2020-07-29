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

import com.alerouge.kyivent.exception.RecordNotFoundException;
import com.alerouge.kyivent.model.VerificationToken;
import com.alerouge.kyivent.model.user.UserAuthoritiesEntity;
import com.alerouge.kyivent.model.user.UserDto;
import com.alerouge.kyivent.model.user.UserEntity;
import com.alerouge.kyivent.repository.user.UserAuthoritiesRepository;
import com.alerouge.kyivent.repository.user.UserRepository;
import com.alerouge.kyivent.repository.user.VerificationTokenRepository;
import com.alerouge.kyivent.utility.UtiData;
import com.alerouge.kyivent.web.error.UserAlreadyExistException;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository tokenRepository;
	
	@Autowired
	private UserAuthoritiesRepository userAuthoritiesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

	
    public UserEntity registerNewUserAccount(final UserDto userDto) {
        if (emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + userDto.getEmail());
        }
        final UserEntity user = new UserEntity();

        user.setNome(userDto.getNome());
        user.setCognome(userDto.getCognome());
        user.setEmail(userDto.getEmail());
        
        user.setDataNascita(Date.valueOf(userDto.getDataNascita()));
        user.setDataIscrizione(UtiData.todayNow());
        
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setSesso(userDto.getSesso());
        
//        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        
        
        UserEntity createdUser = userRepository.save(user);
        
		// tabella user_authorities
	    UserAuthoritiesEntity userAuthoritiesEntity = new UserAuthoritiesEntity();
	    userAuthoritiesEntity.setUserId(createdUser.getId());
	    userAuthoritiesEntity.setAuthority("ROLE_USER");
	    userAuthoritiesRepository.save(userAuthoritiesEntity);

        
        return createdUser;
    }

    private boolean emailExists(final String email) {
        return userRepository.findByEmail(email) != null;
    }

    public void createVerificationTokenForUser(final UserEntity user, final String token) {
        final VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    
	@Override
	public void saveRegisteredUser(UserEntity user) {
        userRepository.save(user);
	}

	@Override
	public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
	}

	@Override
	public UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email);
	} 

}