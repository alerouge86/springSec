package com.alerouge.kyivent.service.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.alerouge.kyivent.enumeration.StatoEventoEnum;
import com.alerouge.kyivent.model.event.EventDto;
import com.alerouge.kyivent.model.event.EventEntity;
import com.alerouge.kyivent.model.event.ParametriSelezioneEvento;
import com.alerouge.kyivent.repository.event.EventoDao;
import com.alerouge.kyivent.repository.event.EventoRepository;
import com.alerouge.kyivent.utility.UtiData;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventoService implements IEventoService {
	
	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private EventoDao eventoDao;
	
	@Override
	public List<EventEntity> getAllEventsAttivi(long userIdCreazione, boolean selectTop5) {
		List<EventEntity> allEventEntity = gestGetAllEventsAttivi(userIdCreazione, selectTop5);
		if(!allEventEntity.isEmpty()) {
			int dateNow = UtiData.formatDateNumeric(UtiData.todayStr());
			int timeNow = UtiData.formatTimeNumeric(UtiData.now());
			for (EventEntity eventEntity: allEventEntity){
				// verifico se l'evento è ancora cancellabile/modificabile (verifico se scaduto)
				if (eventoScaduto(eventEntity, dateNow, timeNow)){
					eventEntity.setScaduto(true);
					eventEntity.setStato(StatoEventoEnum.SCADUTO.toString());
				}
			}
			return allEventEntity;
		} else {
			return new ArrayList<>();
		}
	} 

	/**
	 * Gestione get elementi
	 */
	private List<EventEntity> gestGetAllEventsAttivi(long userIdCreazione, boolean selectTop5) {
		List<EventEntity> allEventEntity = null;
		if (selectTop5){
			allEventEntity = eventoRepository.findTop5ByUserIdCreazioneAndAnnullamentoLogico(userIdCreazione, false, Sort.by(Sort.Direction.DESC, "dataCreazione"));
		} else {
			allEventEntity = eventoRepository.findByUserIdCreazioneAndAnnullamentoLogico(userIdCreazione, false, Sort.by(Sort.Direction.DESC, "dataCreazione"));
		}
		return allEventEntity;
	} 

	/**
	 * Verifica se evento scaduto
	 */
	private boolean eventoScaduto(EventEntity eventEntity, int dateNow, int timeNow) {
		int dataEvento = UtiData.formatDateNumeric(UtiData.getDateStrFromUtilDate(eventEntity.getDataOra()));
		int oraEvento = UtiData.formatTimeNumeric(UtiData.getTimeFromUtilDate(eventEntity.getDataOra()));
		if (dataEvento==dateNow){
			// bisogna verificare l'ora
			if (oraEvento<timeNow){
				// evento scaduto
				return true;
			}
		} else {
			if (dataEvento<dateNow){
				// evento scaduto
				return true;
			}
		}
		return false;
	}

	@Override
	public EventEntity getEvent(long idEvento, long userIdCreazione) {
		Optional<EventEntity> evento = eventoRepository.findByIdAndUserIdCreazione(idEvento, userIdCreazione);
		if (evento.isPresent()){
			return evento.get();
		} else {
			return null;
		}
	}

	@Override
	public void saveEvent(EventDto eventDto, long userIdCreazione) {
		EventEntity eventDaSalvare = null;
		boolean variazione = false;
		Optional<EventEntity> eventoOpt = null;
		if (eventDto.getId()!=null){
			eventoOpt = eventoRepository.findByIdAndUserIdCreazione(eventDto.getId(), userIdCreazione);
			if (eventoOpt.isPresent()){
				variazione = true;
			}
		}
		if (variazione){
			eventDaSalvare = eventoOpt.get();
			eventDaSalvare.setDataModifica(UtiData.todayNow());
		} else {
			eventDaSalvare = new EventEntity();
			eventDaSalvare.setDataCreazione(UtiData.todayNow());
		}
		eventDaSalvare.setCategoriaEventoId(Long.parseLong(eventDto.getCategoriaEvento()));
	    eventDaSalvare.setDescrizione(eventDto.getDescrizione());
	    if (eventDto.getNumeroPartecipanti()!=null){
		    eventDaSalvare.setNumeroPartecipanti(eventDto.getNumeroPartecipanti());
	    } else {
	    	eventDaSalvare.setNumeroPartecipanti(0);
	    }
	    eventDaSalvare.setGestioneListaAttesa(eventDto.isGestioneListaAttesa());
	    Date dataOra = UtiData.convertInDateTime(eventDto.getDataEvento(), eventDto.getOraEvento());
	    eventDaSalvare.setDataOra(dataOra);
	    // ricavo giorno della settimana
	    eventDaSalvare.setGiornoSettimana(UtiData.getDayOfWeek(dataOra));
	    eventDaSalvare.setCittaId(eventDto.getCittaId());
	    eventDaSalvare.setIndirizzo(eventDto.getIndirizzo());
	    if (eventDto.getDurataOre()!=null){
		    eventDaSalvare.setDurataOre(eventDto.getDurataOre());
	    } else {
		    eventDaSalvare.setDurataOre(0);
	    }
	    if (eventDto.getPrezzoPersona()!=null){
		    eventDaSalvare.setPrezzoPersona(eventDto.getPrezzoPersona());
	    } else {
		    eventDaSalvare.setPrezzoPersona(0);
	    }
	    eventDaSalvare.setStato(StatoEventoEnum.ATTIVO.toString());
	    eventDaSalvare.setPrivato(eventDto.isPrivato());
	    eventDaSalvare.setUserIdCreazione(userIdCreazione);
				
		eventoRepository.save(eventDaSalvare);
	}

	@Override
	public void saveEvent(EventEntity eventEntity, long userIdCreazione) {
		eventoRepository.save(eventEntity);
	}
	
	@Override
	public Page<EventEntity> findPaginated(Pageable pageable, ParametriSelezioneEvento parametriSelezioneEvento) {
		List<EventEntity> allEvents = eventoDao.getElencoEventiConQuery(parametriSelezioneEvento);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<EventEntity> list;
        if (allEvents.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, allEvents.size());
            list = allEvents.subList(startItem, toIndex);
        }
        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), allEvents.size());
	}

}