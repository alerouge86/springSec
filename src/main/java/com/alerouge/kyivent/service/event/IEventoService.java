package com.alerouge.kyivent.service.event;

import java.util.List;

import com.alerouge.kyivent.model.event.EventDto;
import com.alerouge.kyivent.model.event.EventEntity;

public interface IEventoService {

	public List<EventEntity> getAllEventsAttivi(long userIdCreazione, boolean selectTop5);

	public EventEntity getEvent(long idEvento, long userIdCreazione);

	public void saveEvent(EventDto eventDto, long userIdCreazione);

	public void saveEvent(EventEntity eventEntity, long userIdCreazione);

}
