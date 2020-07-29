package com.alerouge.kyivent.model.session;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.alerouge.kyivent.model.event.ParametriSelezioneEvento;

/**
 * Gestisce dati ricerca evento in sessione
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserEventSearchSession implements java.io.Serializable{

	private static final long serialVersionUID = 8379474980034736122L;
	
	private ParametriSelezioneEvento parametriSelezioneEvento;

	public void clean(){
		setDati(null);
	}

	public void init(ParametriSelezioneEvento parametriSelezioneEvento){
		setDati(parametriSelezioneEvento);
	}
		
	private void setDati(ParametriSelezioneEvento parametriSelezioneEvento){
		setParametriSelezioneEvento(parametriSelezioneEvento);
	}

	
	public ParametriSelezioneEvento getParametriSelezioneEvento() {
		return parametriSelezioneEvento;
	}

	public void setParametriSelezioneEvento(ParametriSelezioneEvento parametriSelezioneEvento) {
		this.parametriSelezioneEvento = parametriSelezioneEvento;
	}
	
}
