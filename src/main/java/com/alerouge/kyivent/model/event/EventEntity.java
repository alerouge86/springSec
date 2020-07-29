package com.alerouge.kyivent.model.event;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.alerouge.kyivent.model.table.CategoriaEventoEntity;

@Entity
@Table(name="eventi")
public class EventEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="categoriaEventoId")
    private Long categoriaEventoId;
    
    @Column(name="descrizione", length=250)
    private String descrizione;

    @Column(name="numeroPartecipanti")
    private int numeroPartecipanti;

    @Column(name="dataOra", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dataOra;

    @Column(name="giornoSettimana", length=20)
    private String giornoSettimana;

    @Column(name="durataOre")
    private double durataOre;

    @Column(name="cittaId")
    private Long cittaId;
    
    @Column(name="indirizzo", length=250)
    private String indirizzo;
    
    @Column(name="prezzoPersona")
    private double prezzoPersona;
    
    @Column(name="stato", length=100)
    private String stato;
    
    @Column(name="privato")
    private boolean privato;

    @Column(name="scaduto")
    private boolean scaduto;
    
    @Column(name="annullamentoLogico")
    private boolean annullamentoLogico;

    @Column(name="gestioneListaAttesa")
    private boolean gestioneListaAttesa;

    @ManyToOne
    @JoinColumn(name="categoriaEventoId", insertable=false, updatable=false, nullable=false)
    private CategoriaEventoEntity categoriaEvento;
    
    @Column(name="dataCreazione", columnDefinition = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dataCreazione;

    @Column(name="dataModifica", columnDefinition = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dataModifica;

    @Column(name="userIdCreazione")
    private Long userIdCreazione;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCategoriaEventoId() {
		return categoriaEventoId;
	}

	public void setCategoriaEventoId(Long categoriaEventoId) {
		this.categoriaEventoId = categoriaEventoId;
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

	public java.util.Date getDataOra() {
		return dataOra;
	}

	public void setDataOra(java.util.Date dataOra) {
		this.dataOra = dataOra;
	}

	public Long getCittaId() {
		return cittaId;
	}

	public void setCittaId(Long cittaId) {
		this.cittaId = cittaId;
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

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public boolean isPrivato() {
		return privato;
	}

	public void setPrivato(boolean privato) {
		this.privato = privato;
	}

	public java.util.Date getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(java.util.Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public java.util.Date getDataModifica() {
		return dataModifica;
	}

	public void setDataModifica(java.util.Date dataModifica) {
		this.dataModifica = dataModifica;
	}

	public Long getUserIdCreazione() {
		return userIdCreazione;
	}

	public void setUserIdCreazione(Long userIdCreazione) {
		this.userIdCreazione = userIdCreazione;
	}

	public CategoriaEventoEntity getCategoriaEvento() {
		return categoriaEvento;
	}

	public void setCategoriaEvento(CategoriaEventoEntity categoriaEvento) {
		this.categoriaEvento = categoriaEvento;
	}

	public boolean isScaduto() {
		return scaduto;
	}

	public void setScaduto(boolean scaduto) {
		this.scaduto = scaduto;
	}

	public boolean isAnnullamentoLogico() {
		return annullamentoLogico;
	}

	public void setAnnullamentoLogico(boolean annullamentoLogico) {
		this.annullamentoLogico = annullamentoLogico;
	}

	public String getGiornoSettimana() {
		return giornoSettimana;
	}

	public void setGiornoSettimana(String giornoSettimana) {
		this.giornoSettimana = giornoSettimana;
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
