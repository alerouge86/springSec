package com.alerouge.kyivent.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alerouge.kyivent.model.CheckRadioSelectOptionElement;
import com.alerouge.kyivent.model.event.EventDto;
import com.alerouge.kyivent.model.event.EventEntity;
import com.alerouge.kyivent.model.table.CategoriaEventoEntity;
import com.alerouge.kyivent.model.user.UserDto;
import com.alerouge.kyivent.model.user.UserEntity;
import com.alerouge.kyivent.service.event.ICategoriaEventoService;
import com.alerouge.kyivent.service.event.IEventoService;
import com.alerouge.kyivent.service.user.IUserService;
import com.alerouge.kyivent.utility.UtiData;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/event")
public class EventController extends BaseController {

	private static final Logger log = Logger.getLogger(EventController.class);

	@Autowired
	private ICategoriaEventoService categoriaEventoService;

	@Autowired
	private IEventoService eventoService;


	@GetMapping(value ="/home")
	public String homeEvent(Model model){
		if (utenteLoggato()){
			// selezione top 5
			List<EventEntity> allEventi = eventoService.getAllEventsAttivi(userLoggedSession.getUser().getId(), true);
			model.addAttribute("allEventi", allEventi);
			// ricavo numero eventi (per visualizzare pulsante "visualizza tutti"
			model.addAttribute("numeroEventiTotali", eventoService.getAllEventsAttivi(userLoggedSession.getUser().getId(), false).size());
		}
		return "myEvents";
	}

	@GetMapping(value ="/viewAll")
	public String viewAllEvent(Model model){
		// selezione completa
		List<EventEntity> allEventi = eventoService.getAllEventsAttivi(userLoggedSession.getUser().getId(), false);
		model.addAttribute("allEventi", allEventi);
		return "allEvents";
	}

	@GetMapping(value ="/create/{callDaAllEvents}")
	public String createEvent(@PathVariable boolean callDaAllEvents, Model model){
		// preparazione form create event
		if (utenteLoggato()){
			List<CategoriaEventoEntity> allCategorieEvento = categoriaEventoService.getAllCategorie();
			model.addAttribute("event", new EventDto(userLoggedSession.getUser().getCitta().getId(), allCategorieEvento));
			model.addAttribute("callDaAllEvents", callDaAllEvents);
		}
		return "createEvent";
	}
	
	@GetMapping(value ="/duplicate/{idEvento}/{callDaAllEvents}")
	public String duplicateEvent(@PathVariable long idEvento, @PathVariable boolean callDaAllEvents, Model model){
		// preparazione form create event
		List<CategoriaEventoEntity> allCategorieEvento = categoriaEventoService.getAllCategorie();
		
		// ricavo evento da duplicare
		EventEntity eventoDaDuplicare = eventoService.getEvent(idEvento, userLoggedSession.getUser().getId());

		// oggetto dto
		EventDto eventDto = creaEventoDtoDaEntity(true, eventoDaDuplicare, allCategorieEvento);
		model.addAttribute("event", eventDto);
		model.addAttribute("callDaAllEvents", callDaAllEvents);
		
		return "createEvent";
	}

	@GetMapping(value ="/view/{idEvento}/{callDaAllEvents}")
	public String viewEvent(@PathVariable long idEvento, @PathVariable boolean callDaAllEvents, Model model){
		// preparazione form create event
		List<CategoriaEventoEntity> allCategorieEvento = categoriaEventoService.getAllCategorie();
		
		// ricavo evento da duplicare
		EventEntity eventoDaVisualizzare = eventoService.getEvent(idEvento, userLoggedSession.getUser().getId());

		// oggetto dto
		EventDto eventDto = creaEventoDtoDaEntity(false, eventoDaVisualizzare, allCategorieEvento);
		model.addAttribute("event", eventDto);
		model.addAttribute("flgConsultazione", true);
		model.addAttribute("fieldDisabled", "disabled");
		model.addAttribute("callDaAllEvents", callDaAllEvents);
		
		return "createEvent";
	}

	@GetMapping(value ="/modifica/{idEvento}/{callDaAllEvents}")
	public String modificaEvent(@PathVariable long idEvento, @PathVariable boolean callDaAllEvents, Model model){
		// preparazione form create event
		List<CategoriaEventoEntity> allCategorieEvento = categoriaEventoService.getAllCategorie();
		
		// ricavo evento da duplicare
		EventEntity eventoDaVisualizzare = eventoService.getEvent(idEvento, userLoggedSession.getUser().getId());

		// oggetto dto
		EventDto eventDto = creaEventoDtoDaEntity(false, eventoDaVisualizzare, allCategorieEvento);
		model.addAttribute("event", eventDto);
		model.addAttribute("flgModifica", true);
		model.addAttribute("callDaAllEvents", callDaAllEvents);
		
		return "createEvent";
	}
	
	@GetMapping(value ="/delete/{idEvento}/{callDaAllEvents}")
	public String deleteEvent(@PathVariable long idEvento, boolean callDaAllEvents, Model model){
		// ricavo evento da duplicare
		EventEntity eventoDaCancellare = eventoService.getEvent(idEvento, userLoggedSession.getUser().getId());

		// annullamento logico
		eventoDaCancellare.setAnnullamentoLogico(true);
		eventoService.saveEvent(eventoDaCancellare, userLoggedSession.getUser().getId());
		model.addAttribute("callDaAllEvents", callDaAllEvents);
		
		return "redirect:/event/home";
	}
	
	@PostMapping(value ="/save")
	public String saveEvent(@Valid @ModelAttribute("event") EventDto event, RedirectAttributes redirectAttributes, Model model, BindingResult result, HttpServletRequest request){
		if (result.hasErrors()) {
			// dispatch form (per visualizzazione errori)
			model.addAttribute("event", event);
			return "createEvent";
		}
		
		// inserimento evento
		eventoService.saveEvent(event, userLoggedSession.getUser().getId());
		
		// TODO: gestire messages (in lingue) per java
		redirectAttributes.addFlashAttribute("showAlert", true);
		if ("true".equals(request.getParameter("flgModifica"))){
			redirectAttributes.addFlashAttribute("msgAlert", "L'evento: \"" + event.getDescrizione() + "\" è stato salvato con successo!");
		} else {
			redirectAttributes.addFlashAttribute("msgAlert", "Il nuovo evento: \"" + event.getDescrizione() + "\" è stato creato con successo!");
		}
		return "redirect:/event/home";
	}

	/**
	 * Dato l'oggetto entity, ritorna l'oggetto dto (usato per duplicazione e consultazione)
	 */
	private EventDto creaEventoDtoDaEntity(boolean flgNuovo, EventEntity eventoEntity, List<CategoriaEventoEntity> allCategorieEvento) {
		EventDto eventDto = new EventDto(userLoggedSession.getUser().getCitta().getId(), allCategorieEvento);
		eventDto.setDescrizione(eventoEntity.getDescrizione());
		eventDto.setCategoriaEvento(String.valueOf(eventoEntity.getCategoriaEvento().getId()));
		eventDto.setNumeroPartecipanti(eventoEntity.getNumeroPartecipanti());
		eventDto.setGestioneListaAttesa(eventoEntity.isGestioneListaAttesa());
		eventDto.setPrezzoPersona(eventoEntity.getPrezzoPersona());
		eventDto.setIndirizzo(eventoEntity.getIndirizzo());
		eventDto.setDurataOre(eventoEntity.getDurataOre());
		eventDto.setPrivato(eventoEntity.isPrivato());
		eventDto.setCittaId(eventoEntity.getCittaId());
		if (!flgNuovo){
			eventDto.setId(eventoEntity.getId());
			eventDto.setDataEvento(UtiData.getSqlDateFromUtilDate(eventoEntity.getDataOra()));
			eventDto.setOraEvento(UtiData.getTimeFromUtilDate(eventoEntity.getDataOra()));
		}
		return eventDto;
	}

}
