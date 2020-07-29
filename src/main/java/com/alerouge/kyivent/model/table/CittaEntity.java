package com.alerouge.kyivent.model.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="citta")
public class CittaEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="descrizione", length=250)
    private String descrizione;

    @ManyToOne
    @JoinColumn(name="nazioneId", nullable=false)
    private NazioneEntity nazione;
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public NazioneEntity getNazione() {
		return nazione;
	}

	public void setNazione(NazioneEntity nazione) {
		this.nazione = nazione;
	}

}
