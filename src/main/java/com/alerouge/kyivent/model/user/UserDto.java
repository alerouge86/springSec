package com.alerouge.kyivent.model.user;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.alerouge.kyivent.model.CheckRadioSelectOptionElement;
import com.alerouge.kyivent.validation.PasswordMatches;
import com.alerouge.kyivent.validation.ValidEmail;
import com.alerouge.kyivent.validation.ValidPassword;

/**
 * Data Transfer Object per l'entity utente
 */
//@PasswordMatches
public class UserDto {

	@NotEmpty
	@Size(min=2, max=255)
	private String nome;
	
	@NotEmpty
	@Size(min=2, max=255)
	private String cognome;
	
	@NotEmpty
	@Size(min=2, max=255)
    @ValidEmail
	private String email;
	
	@NotEmpty
	private String dataNascita;
	
    @ValidPassword
	private String password;
    
//	@NotEmpty
//    private String matchingPassword;

	
	@NotEmpty
    @Size(min = 1)
	private String sesso;
	// gestione radio button per il campo tipoUser
	private List<CheckRadioSelectOptionElement> allSesso = Arrays.asList(new CheckRadioSelectOptionElement("F", "Donna"),
																	     new CheckRadioSelectOptionElement("M", "Uomo"));
	
	
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
	public String getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
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
	public List<CheckRadioSelectOptionElement> getAllSesso() {
		return allSesso;
	}
	public void setAllSesso(List<CheckRadioSelectOptionElement> allSesso) {
		this.allSesso = allSesso;
	}
//	public String getMatchingPassword() {
//		return matchingPassword;
//	}
//	public void setMatchingPassword(String matchingPassword) {
//		this.matchingPassword = matchingPassword;
//	}
}
