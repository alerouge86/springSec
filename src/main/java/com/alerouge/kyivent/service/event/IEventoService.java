package com.alerouge.kyivent.service.event;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alerouge.kyivent.model.event.EventDto;
import com.alerouge.kyivent.model.event.EventEntity;
import com.alerouge.kyivent.model.event.ParametriSelezioneEvento;

public interface IEventoService {

	public List<EventEntity> getAllEventsAttivi(long userIdCreazione, boolean selectTop5);

	public EventEntity getEvent(long idEvento, long userIdCreazione);

	public void saveEvent(EventDto eventDto, long userIdCreazione);

	public void saveEvent(EventEntity eventEntity, long userIdCreazione);

	/**
	 * Gestione paginazione eventi (trova evento)
	 */
	public Page<EventEntity> findPaginated(Pageable pageable, ParametriSelezioneEvento parametriSelezioneEvento);

}
