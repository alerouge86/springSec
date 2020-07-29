package com.alerouge.kyivent.model.event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alerouge.kyivent.model.CheckRadioSelectOptionElement;
import com.alerouge.kyivent.model.table.CategoriaEventoEntity;

/**
 * Data Transfer Object per parametri selezione evento
 */
public class EventSearchDto {

	private String descrizione;
	private String categoriaEvento;
	private List<CheckRadioSelectOptionElement> allCategorieEvento;
	private String dataEvento;
	private String indirizzo;
	private String stato;
	private long cittaId;
	private String giornoSettimana;
	private List<CheckRadioSelectOptionElement> allGiorniSettimana = Arrays.asList(new CheckRadioSelectOptionElement("MONDAY", "MONDAY"),
																				   new CheckRadioSelectOptionElement("TUESDAY", "TUESDAY"),
																				   new CheckRadioSelectOptionElement("WEDNESDAY", "WEDNESDAY"),
																				   new CheckRadioSelectOptionElement("THURSDAY", "THURSDAY"),
																				   new CheckRadioSelectOptionElement("FRIDAY", "FRIDAY"),
																				   new CheckRadioSelectOptionElement("SATURDAY", "SATURDAY"),
																				   new CheckRadioSelectOptionElement("SUNDAY", "SUNDAY"));
    
	public EventSearchDto(){
		super();
	}
	
	public EventSearchDto(long cittaId, List<CategoriaEventoEntity> parAllCategorieEvento){
		this.cittaId = cittaId;
		// carico l'elenco delle categorie evento
		this.allCategorieEvento = new ArrayList<>();
		for (CategoriaEventoEntity categoria: parAllCategorieEvento){
			this.allCategorieEvento.add(new CheckRadioSelectOptionElement(String.valueOf(categoria.getId()), categoria.getDescrizioneIta()));
		}
	}

    
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public List<CheckRadioSelectOptionElement> getAllCategorieEvento() {
		return allCategorieEvento;
	}
	public void setAllCategorieEvento(List<CheckRadioSelectOptionElement> allCategorieEvento) {
		this.allCategorieEvento = allCategorieEvento;
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
	public long getCittaId() {
		return cittaId;
	}
	public void setCittaId(long cittaId) {
		this.cittaId = cittaId;
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

	public String getCategoriaEvento() {
		return categoriaEvento;
	}

	public void setCategoriaEvento(String categoriaEvento) {
		this.categoriaEvento = categoriaEvento;
	}

	public List<CheckRadioSelectOptionElement> getAllGiorniSettimana() {
		return allGiorniSettimana;
	}

	public void setAllGiorniSettimana(List<CheckRadioSelectOptionElement> allGiorniSettimana) {
		this.allGiorniSettimana = allGiorniSettimana;
	}

}
