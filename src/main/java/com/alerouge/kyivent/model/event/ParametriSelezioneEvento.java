package com.alerouge.kyivent.model.event;

/**
 * Gestisce parametri selezione a video per ricerca eventi
 */
public class ParametriSelezioneEvento {

	private long cittaId;
	private long categoriaEventoId;
	private String descrizioneEvento;
	private String dataEvento;
	private String indirizzo;
	private String stato;
	private String giornoSettimana;
	
	public ParametriSelezioneEvento(long cittaId){
		this.cittaId = cittaId;
	}
	
	public long getCittaId() {
		return cittaId;
	}
	public void setCittaId(long cittaId) {
		this.cittaId = cittaId;
	}
	public long getCategoriaEventoId() {
		return categoriaEventoId;
	}
	public void setCategoriaEventoId(long categoriaEventoId) {
		this.categoriaEventoId = categoriaEventoId;
	}
	public String getDescrizioneEvento() {
		return descrizioneEvento;
	}
	public void setDescrizioneEvento(String descrizioneEvento) {
		this.descrizioneEvento = descrizioneEvento;
	}
	public String getDataEvento() {
		return dataEvento;
	}
	public void setDataEvento(String dataEvento) {
		this.dataEvento = dataEvento;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public String getGiornoSettimana() {
		return giornoSettimana;
	}
	public void setGiornoSettimana(String giornoSettimana) {
		this.giornoSettimana = giornoSettimana;
	}
}
