package com.alerouge.kyivent.model.table;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="nazioni")
public class NazioneEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="descrizione", length=250)
    private String descrizione;

    @OneToMany(mappedBy="nazione")
    private Set<CittaEntity> citta;
    
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

	public Set<CittaEntity> getCitta() {
		return citta;
	}

	public void setCitta(Set<CittaEntity> citta) {
		this.citta = citta;
	}

}
