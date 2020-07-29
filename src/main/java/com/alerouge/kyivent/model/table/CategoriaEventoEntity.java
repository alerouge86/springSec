package com.alerouge.kyivent.model.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="categoriaEvento")
public class CategoriaEventoEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="descrizioneIta", length=200)
    private String descrizioneIta;

    @Column(name="descrizioneEng", length=200)
    private String descrizioneEng;

    @Column(name="descrizioneRus", length=200)
    private String descrizioneRus;

    @Column(name="descrizioneUa", length=200)
    private String descrizioneUa;

    @Column(name="iconaFntAws", length=30)
    private String iconaFntAws;

    @Column(name="dataCreazione", columnDefinition = "datetime")
    private java.util.Date dataCreazione;

    @Column(name="userIdCreazione")
    private Long userIdCreazione;

    @Column(name="creazioneAdmin")
    private boolean creazioneAdmin;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizioneIta() {
		return descrizioneIta;
	}

	public void setDescrizioneIta(String descrizioneIta) {
		this.descrizioneIta = descrizioneIta;
	}

	public String getDescrizioneEng() {
		return descrizioneEng;
	}

	public void setDescrizioneEng(String descrizioneEng) {
		this.descrizioneEng = descrizioneEng;
	}

	public String getDescrizioneRus() {
		return descrizioneRus;
	}

	public void setDescrizioneRus(String descrizioneRus) {
		this.descrizioneRus = descrizioneRus;
	}

	public String getDescrizioneUa() {
		return descrizioneUa;
	}

	public void setDescrizioneUa(String descrizioneUa) {
		this.descrizioneUa = descrizioneUa;
	}

	public java.util.Date getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(java.util.Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Long getUserIdCreazione() {
		return userIdCreazione;
	}

	public void setUserIdCreazione(Long userIdCreazione) {
		this.userIdCreazione = userIdCreazione;
	}

	public boolean isCreazioneAdmin() {
		return creazioneAdmin;
	}

	public void setCreazioneAdmin(boolean creazioneAdmin) {
		this.creazioneAdmin = creazioneAdmin;
	}

	public String getIconaFntAws() {
		return iconaFntAws;
	}

	public void setIconaFntAws(String iconaFntAws) {
		this.iconaFntAws = iconaFntAws;
	}

}
