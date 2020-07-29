package com.alerouge.kyivent.repository.event;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alerouge.kyivent.model.event.EventEntity;


@Repository
public interface EventoRepository extends CrudRepository<EventEntity, Long> {

	/**
	 * Seleziona tutti i record attivi
	 */
	public List<EventEntity> findByAnnullamentoLogico(boolean annullamentoLogico, Sort sort);

	/**
	 * Seleziona il record attivo (di un utente)
	 */
	public Optional<EventEntity> findByIdAndUserIdCreazione(long id, long userIdCreazione);
	
	/**
	 * Seleziona tutti i record attivi (di un utente)
	 */
	public List<EventEntity> findByUserIdCreazioneAndAnnullamentoLogico(long userIdCreazione, boolean annullamentoLogico, Sort sort);
	
	/**
	 * Seleziona top 5 record attivi (di un utente)
	 */
	public List<EventEntity> findTop5ByUserIdCreazioneAndAnnullamentoLogico(long userIdCreazione, boolean annullamentoLogico, Sort sort);

}
