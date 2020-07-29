package com.alerouge.kyivent.model.user;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.alerouge.kyivent.model.table.CittaEntity;

@Entity
@Table(name="users")
public class UserEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome", length=250)
    private String nome;

    @Column(name="cognome", length=250)
    private String cognome;

    @Column(name="email", length=250)
    private String email;

    @Column(name="dataNascita", columnDefinition = "date")
    private Date dataNascita;

    @Column(name="dataIscrizione", columnDefinition = "datetime")
    private java.util.Date dataIscrizione;

    @Column(name="password", length=100)
    private String password;

    @Column(name="sesso", length=1)
    private String sesso;

    @Column(name="enabled")
    private boolean enabled;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cittaId", referencedColumnName = "id")
    private CittaEntity citta;
    
    public UserEntity() {
        super();
        this.enabled=false;
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public java.util.Date getDataIscrizione() {
		return dataIscrizione;
	}

	public void setDataIscrizione(java.util.Date dataIscrizione) {
		this.dataIscrizione = dataIscrizione;
	}

	public CittaEntity getCitta() {
		return citta;
	}

	public void setCitta(CittaEntity citta) {
		this.citta = citta;
	}

}
