package com.alerouge.kyivent.model.session;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.alerouge.kyivent.model.user.UserEntity;

/**
 * Gestisce dati utente loggato
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserLoggedSession implements java.io.Serializable{

	private static final long serialVersionUID = 195966139358504261L;

	// Le entities lato DB devono essere sempre disaccopiate dagli oggetti che si espongono lato FE.
	// Dovresti fare un DTO(Data Trans Object) esempio UserEntityDTO
	private UserEntity user;

	public void clean(){
		setDati(null);
	}

	public void init(UserEntity user){
		setDati(user);
	}
		
	private void setDati(UserEntity user){
		setUser(user);
	}

	
	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
	
}
