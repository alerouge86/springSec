package com.alerouge.kyivent.configuration;

public class GeneralConfig {
	
	private String applicationName;
	private String applicationVersion;
	private String applicationCity;
	private long applicationCityId;

	public String getApplicationVersion() {
		return applicationVersion;
	}

	public void setApplicationVersion(String applicationVersion) {
		this.applicationVersion = applicationVersion;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getApplicationCity() {
		return applicationCity;
	}

	public void setApplicationCity(String applicationCity) {
		this.applicationCity = applicationCity;
	}

	public long getApplicationCityId() {
		return applicationCityId;
	}

	public void setApplicationCityId(long applicationCityId) {
		this.applicationCityId = applicationCityId;
	}

}
