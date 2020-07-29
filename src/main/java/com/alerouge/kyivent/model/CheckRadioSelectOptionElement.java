package com.alerouge.kyivent.model;

/**
 * Classe utilizzata per rappresentare le select box, i radio button e i check box con thymeleaf
 */
public class CheckRadioSelectOptionElement {

	private String chiaveOption;
	private String descrizioneOption;
	private boolean checked;

	public CheckRadioSelectOptionElement(String chiaveOption, String descrizioneOption) {
		super();
		this.chiaveOption = chiaveOption;
		this.descrizioneOption = descrizioneOption;
	}

	public String getChiaveOption() {
		return chiaveOption;
	}
	public void setChiaveOption(String chiaveOption) {
		this.chiaveOption = chiaveOption;
	}
	public String getDescrizioneOption() {
		return descrizioneOption;
	}
	public void setDescrizioneOption(String descrizioneOption) {
		this.descrizioneOption = descrizioneOption;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
