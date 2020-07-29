package com.alerouge.kyivent.repository.event;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.alerouge.kyivent.model.event.EventEntity;
import com.alerouge.kyivent.model.event.ParametriSelezioneEvento;
import com.alerouge.kyivent.utility.UtiData;
import com.alerouge.kyivent.utility.UtiStringa;


@Repository
public class EventoDao {

	@PersistenceContext
	private EntityManager entityManger;

	/**
	 * Ricava elenco eventi con gestione parametri di selezione
	 */
	public List<EventEntity> getElencoEventiConQuery(ParametriSelezioneEvento parametriSelezioneEvento){

		// TODO: aggiungere in where con dataOra >= today (per non visualizzare eventi scaduti)
		
		// TODO: modificare campo dataOra e utilizzare 2 campi
		
		// costruzione where
		StringBuilder whereSql = new StringBuilder
				("where cittaId = :cittaId " +
				 "and privato = false ");
		
		whereSql.append("and annullamentoLogico=false ");
		if (parametriSelezioneEvento.getCategoriaEventoId()!=0){
			whereSql.append("and categoriaEventoId = :categoriaEventoId ");
		}
		if (!UtiStringa.strVoid(UtiStringa.trimNotNull(parametriSelezioneEvento.getDescrizioneEvento()))){
			whereSql.append("and descrizione like :descrizioneEvento ");
		}
		if (!UtiStringa.strVoid(UtiStringa.trimNotNull(parametriSelezioneEvento.getDataEvento()))){
			whereSql.append("and dataOra >= :dataOraIniGiorno and dataOra <= :dataOraFineGiorno ");
		}
		if (!UtiStringa.strVoid(UtiStringa.trimNotNull(parametriSelezioneEvento.getIndirizzo()))){
			whereSql.append("and indirizzo like :indirizzo ");
		}
		if (!UtiStringa.strVoid(UtiStringa.trimNotNull(parametriSelezioneEvento.getStato()))){
			whereSql.append("and stato = :stato ");
		}
		if (!UtiStringa.strVoid(UtiStringa.trimNotNull(parametriSelezioneEvento.getGiornoSettimana()))){
			whereSql.append("and giornoSettimana = :giornoSettimana ");
		}
		
		TypedQuery<EventEntity> query = entityManger.createQuery(
				"select e from EventEntity e " + 
				 whereSql.toString() + 
				"order by dataOra ", EventEntity.class);
				
		// set parametri where
		query.setParameter("cittaId", parametriSelezioneEvento.getCittaId());
		if (parametriSelezioneEvento.getCategoriaEventoId()!=0){
			query.setParameter("categoriaEventoId", parametriSelezioneEvento.getCategoriaEventoId());
		}
		if (!UtiStringa.strVoid(UtiStringa.trimNotNull(parametriSelezioneEvento.getDescrizioneEvento()))){
			query.setParameter("descrizioneEvento", parametriSelezioneEvento.getDescrizioneEvento()+"%");
		}
		if (!UtiStringa.strVoid(UtiStringa.trimNotNull(parametriSelezioneEvento.getDataEvento()))){
			query.setParameter("dataOraIniGiorno", UtiData.getDataInizioGiorno(parametriSelezioneEvento.getDataEvento()));
			query.setParameter("dataOraFineGiorno", UtiData.getDataFineGiorno(parametriSelezioneEvento.getDataEvento()));
		}
		if (!UtiStringa.strVoid(UtiStringa.trimNotNull(parametriSelezioneEvento.getIndirizzo()))){
			query.setParameter("indirizzo", parametriSelezioneEvento.getIndirizzo()+"%");
		}
		if (!UtiStringa.strVoid(UtiStringa.trimNotNull(parametriSelezioneEvento.getStato()))){
			query.setParameter("stato", parametriSelezioneEvento.getStato());
		}
		if (!UtiStringa.strVoid(UtiStringa.trimNotNull(parametriSelezioneEvento.getGiornoSettimana()))){
			query.setParameter("giornoSettimana", parametriSelezioneEvento.getGiornoSettimana());
		}
		
		return query.getResultList();
	}

}

