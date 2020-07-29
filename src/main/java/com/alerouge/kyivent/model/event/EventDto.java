package com.alerouge.kyivent.model.event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.alerouge.kyivent.model.CheckRadioSelectOptionElement;
import com.alerouge.kyivent.model.table.CategoriaEventoEntity;

/**
 * Data Transfer Object per create event
 */
public class EventDto {

	private Long id;
	
	@NotEmpty
	@Size(min=2, max=250)
	private String descrizione;

	@NotEmpty
	private String categoriaEvento;
	private List<CheckRadioSelectOptionElement> allCategorieEvento;

	private int numeroPartecipanti;

	private double prezzoPersona;

	@NotEmpty
	private String dataEvento;

	@NotEmpty
	private String oraEvento;

	@NotEmpty
	@Size(min=2, max=250)
	private String indirizzo;

	private boolean privato;

	private long cittaId;
	
	private double durataOre;

    private boolean gestioneListaAttesa;

	public EventDto(){
		super();
	}
	
	public EventDto(long cittaId, List<CategoriaEventoEntity> parAllCategorieEvento){
		this.cittaId = cittaId;
		this.gestioneListaAttesa = true;	// default
		// carico l'elenco delle categorie evento
		this.allCategorieEvento = new ArrayList<>();
		for (CategoriaEventoEntity categoria: parAllCategorieEvento){
			this.allCategorieEvento.add(new CheckRadioSelectOptionElement(String.valueOf(categoria.getId()), categoria.getDescrizioneIta()));
		}
	}
	
	public String getCategoriaEvento() {
		return categoriaEvento;
	}
	public void setCategoriaEvento(String categoriaEvento) {
		this.categoriaEvento = categoriaEvento;
	}
	public List<CheckRadioSelectOptionElement> getAllCategorieEvento() {
		return allCategorieEvento;
	}
	public void setAllCategorieEvento(List<CheckRadioSelectOptionElement> allCategorieEvento) {
		this.allCategorieEvento = allCategorieEvento;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getNumeroPartecipanti() {
		return numeroPartecipanti;
	}

	public void setNumeroPartecipanti(int numeroPartecipanti) {
		this.numeroPartecipanti = numeroPartecipanti;
	}

	public String getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(String dataEvento) {
		this.dataEvento = dataEvento;
	}

	public String getOraEvento() {
		return oraEvento;
	}

	public void setOraEvento(String oraEvento) {
		this.oraEvento = oraEvento;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public double getPrezzoPersona() {
		return prezzoPersona;
	}

	public void setPrezzoPersona(double prezzoPersona) {
		this.prezzoPersona = prezzoPersona;
	}

	public boolean isPrivato() {
		return privato;
	}

	public void setPrivato(boolean privato) {
		this.privato = privato;
	}

	public long getCittaId() {
		return cittaId;
	}

	public void setCittaId(long cittaId) {
		this.cittaId = cittaId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getDurataOre() {
		return durataOre;
	}

	public void setDurataOre(double durataOre) {
		this.durataOre = durataOre;
	}

	public boolean isGestioneListaAttesa() {
		return gestioneListaAttesa;
	}

	public void setGestioneListaAttesa(boolean gestioneListaAttesa) {
		this.gestioneListaAttesa = gestioneListaAttesa;
	}

}
