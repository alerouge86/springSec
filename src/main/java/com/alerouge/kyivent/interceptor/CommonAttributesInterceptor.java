package com.alerouge.kyivent.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alerouge.kyivent.configuration.GeneralConfig;
import com.alerouge.kyivent.model.session.UserLoggedSession;
import com.alerouge.kyivent.model.user.UserEntity;
import com.alerouge.kyivent.utility.BeanUtil;
import com.alerouge.kyivent.utility.UtiStringa;

/**
 * Classe interceptor usata per settare i dati (attributi) comuni in tutte le view (per esempio, beanMenu, datiSessione, etc)
 */
public class CommonAttributesInterceptor extends HandlerInterceptorAdapter {

	// TODO: capire come gestire i log in un file (usando il tomcat embedded)
	private static final Logger log = Logger.getLogger(CommonAttributesInterceptor.class);

	@Autowired
	private UserLoggedSession userLoggedSession;
	
	/**
	 * Gestisce la set degli attributi comuni. Chiamata in "postHandle" (ossia appena concluse le logiche del controller ma non ancora fatto il render della view)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
		// logica controller name
		// logiche fatte solo se chiamata da "*Controller"
		if (modelAndView!=null && logicaControllerName(handler, modelAndView)){
			GeneralConfig generalConfig = BeanUtil.getBean(GeneralConfig.class);
			// utente loggato
			UserEntity userLogged = null;
			if (userLoggedSession!=null && userLoggedSession.getUser()!=null){
				userLogged = userLoggedSession.getUser();
			}
			setAttributeGeneraliPage(generalConfig, userLogged, modelAndView);
		}
	}

	/**
	 * Usato per settare gli attributi comuni per tutte le pagine (titolo pagina, title, etc)
	 */
	public static void setAttributeGeneraliPage(GeneralConfig generalConfig, UserEntity userLogged, ModelAndView modelAndView){
		modelAndView.addObject("applicationName", generalConfig.getApplicationName());
		modelAndView.addObject("applicationVersion", generalConfig.getApplicationVersion());
		modelAndView.addObject("applicationCity", generalConfig.getApplicationCity());
		// bold per home
		modelAndView.addObject("applicationNameBold", "<b>"+generalConfig.getApplicationName()+"</b>");
		modelAndView.addObject("applicationVersionBold", "<b>"+generalConfig.getApplicationVersion()+"</b>");
		modelAndView.addObject("applicationCityBold", "<b>"+generalConfig.getApplicationCity()+"</b>");
		// utente loggato
		if (userLogged!=null){
			modelAndView.addObject("userLogged", userLogged);
		}
	}
	
	/**
	 * Logica nome controller
	 */
	private boolean logicaControllerName(Object handler, ModelAndView modelAndView) {
		// verifico se chiamata da "Controller"
		String controllerName = null;
		boolean callDaController = false;
		try {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			controllerName = handlerMethod.getBeanType().getSimpleName();
			
			if (!UtiStringa.strVoid(UtiStringa.trimNotNull(controllerName)) && controllerName.endsWith("Controller")){
				callDaController = true;
			}
		} catch (Exception e) {
			log.error(e);
		}
		return callDaController;
	}

}
