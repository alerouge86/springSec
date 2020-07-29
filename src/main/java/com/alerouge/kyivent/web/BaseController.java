package com.alerouge.kyivent.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.alerouge.kyivent.model.session.UserLoggedSession;

public abstract class BaseController {

	@Autowired
	protected UserLoggedSession userLoggedSession;
	
}
