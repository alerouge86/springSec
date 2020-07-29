package com.alerouge.kyivent.web;

import java.util.Calendar;
import java.util.Locale;

import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alerouge.kyivent.model.user.UserDto;
import com.alerouge.kyivent.event.OnRegistrationCompleteEvent;
import com.alerouge.kyivent.model.VerificationToken;
import com.alerouge.kyivent.model.user.UserAuthoritiesEntity;
import com.alerouge.kyivent.model.user.UserEntity;
import com.alerouge.kyivent.service.user.IUserService;
import com.alerouge.kyivent.service.user.UserService;
import com.alerouge.kyivent.utility.UtiData;
import com.alerouge.kyivent.utility.UtiLog;
import com.alerouge.kyivent.web.error.UserAlreadyExistException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	private static final Logger log = Logger.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	private MessageSource messages;


	@GetMapping(value ="/register")
	public String registerPage(Model model){
    	// se utente gia loggato -> page not found
    	if (utenteLoggato()){
	        return "error/page404";
		} else {
			// preparazione form per registrazione
			model.addAttribute("user", new UserDto());
			return "register";
		}
	}

	@PostMapping(value ="/register")
	public ModelAndView registerUser(@Valid @ModelAttribute("user") UserDto user, BindingResult result, HttpServletRequest request){
//		try {
			
			
			if (result.hasErrors()) {
				// dispatch form (per visualizzazione errori)
				return new ModelAndView("register", "user", user);
			}

			// inserimento utente
			final UserEntity registered = userService.registerNewUserAccount(user);

			// TODO: da riattivare
			// logica invio email attivazione utente
//            final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
//            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));

//		} catch (final UserAlreadyExistException uaeEx) {
//			ModelAndView mav = new ModelAndView("registration", "user", user);
//			String errMessage = messages.getMessage("message.regError", null, request.getLocale());
//			mav.addObject("message", errMessage);
//			
//			// gestione eccezione
//			String echoTokenError = UtiLog.gestioneEccezione(log, e, generalConfig.getApplicationEnvironment());
//			showAlertError(model, messageSource.getMessage(MESSAGE_SOURCE_DESCRIZIONE_ERRORE, new Object[] {echoTokenError}, datiSessione.getLocale()));
//
//			return mav;
//		} catch (final RuntimeException ex) {
//			log.warn("Unable to register user", ex);
//			
//			// gestione eccezione
//			String echoTokenError = UtiLog.gestioneEccezione(log, e, generalConfig.getApplicationEnvironment());
//			showAlertError(model, messageSource.getMessage(MESSAGE_SOURCE_DESCRIZIONE_ERRORE, new Object[] {echoTokenError}, datiSessione.getLocale()));
//
//			return new ModelAndView("emailError", "user", user);
			
			
//		} catch (Exception e) {
//			// gestione eccezione
//			long idUtente = -1;
//			if (userLoggedSession!=null && userLoggedSession.getUser()!=null){
//				idUtente = userLoggedSession.getUser().getId();
//			}
//			String echoTokenError = UtiLog.gestioneEccezione(log, e, idUtente);
//		}

		return new ModelAndView("successRegister", "user", user);
	}

	@GetMapping("/registrationConfirm")
	public String confirmRegistration(final HttpServletRequest request, final Model model, @RequestParam("token") final String token) {
    	// se utente gia loggato -> page not found
    	if (utenteLoggato()){
	        return "error/page404";
		} else {
			final Locale locale = request.getLocale();

			final VerificationToken verificationToken = userService.getVerificationToken(token);
			if (verificationToken == null) {
				final String message = messages.getMessage("auth.message.invalidToken", null, locale);
				model.addAttribute("message", message);
				return "redirect:/badUser.html?lang=" + locale.getLanguage();
			}

			final UserEntity user = verificationToken.getUser();
			final Calendar cal = Calendar.getInstance();
			if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
				model.addAttribute("message", messages.getMessage("auth.message.expired", null, locale));
				model.addAttribute("expired", true);
				model.addAttribute("token", token);
				return "redirect:/badUser.html?lang=" + locale.getLanguage();
			}

			user.setEnabled(true);
			userService.saveRegisteredUser(user);

			// TODO: gestire il messaggio 
			model.addAttribute("message", messages.getMessage("message.accountVerified", null, locale));

			return "login";
		}
	}


	@GetMapping(value ="/forgotPassword")
	public String forgotPassword(Model model){

		// TODO: da gestire

		return "";
	}


}
