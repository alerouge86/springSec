package com.alerouge.kyivent.exception;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.alerouge.kyivent.configuration.GeneralConfig;
import com.alerouge.kyivent.interceptor.CommonAttributesInterceptor;
import com.alerouge.kyivent.model.session.UserLoggedSession;
import com.alerouge.kyivent.model.user.UserEntity;
import com.alerouge.kyivent.utility.BeanUtil;
import com.alerouge.kyivent.utility.UtiLog;

/**
 * Gestione eccezioni tra cui: "pageNotFound" e "dimensione max upload file"
 * @author Alessandro Rossi
 * @since 10 nov 2019
 * @version 2.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log = Logger.getLogger(GlobalExceptionHandler.class);

	
	@Autowired
	protected UserLoggedSession userLoggedSession;

    @Autowired
    protected MessageSource messageSource;

    
    /**
     * Gestione eccezioni non ancora gestite tramite try/catch nei controller e fa il dispatch della pagina d'errore
     * @author Alessandro Rossi
     * @since 05 mar 2020
     */
	@ExceptionHandler(Exception.class)
    public ModelAndView handleRuntimeExceptionException(Exception ex, Model model) {
		long idUtente = -1;
		UserEntity userLogged = null;
		if (userLoggedSession!=null && userLoggedSession.getUser()!=null){
			userLogged = userLoggedSession.getUser();
			idUtente = userLogged.getId();
		}
		String echoTokenError = UtiLog.gestioneEccezione(log, ex, idUtente);
		model.addAttribute("echoTokenError", echoTokenError);

		// attributi comuni della pagina
		ModelAndView modelAndView = new ModelAndView("error/pageErroreEccezioni");
		GeneralConfig generalConfig = BeanUtil.getBean(GeneralConfig.class);
		CommonAttributesInterceptor.setAttributeGeneraliPage(generalConfig, userLogged, modelAndView);
		return modelAndView;
    }
    
}