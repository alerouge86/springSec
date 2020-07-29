package com.alerouge.kyivent.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.alerouge.kyivent.configuration.GeneralConfig;
import com.alerouge.kyivent.model.session.UserLoggedSession;

public abstract class BaseController {

	@Autowired
	protected UserLoggedSession userLoggedSession;
	
	@Autowired
	protected GeneralConfig generalConfig;
	
    /**
     * Verifica se un utente (user o admin) è già loggato
     */
    protected boolean utenteLoggato() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean utenteLoggato = false;
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority authority: authorities){
			if (authority.getAuthority().contains("ROLE_USER") || authority.getAuthority().contains("ROLE_ADMIN")){
				utenteLoggato = true;
			}
		}
		return utenteLoggato;
    }

}
