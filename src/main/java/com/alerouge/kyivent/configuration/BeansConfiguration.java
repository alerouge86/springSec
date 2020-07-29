package com.alerouge.kyivent.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Classe di configurazione contenente i beans personalizzati (creati ad hoc per l'applicazione)
 * @author Alessandro Rossi
 * @since 02 gen 2020
 * @version 2.0
 */
@Configuration
@EnableScheduling
@PropertySource(value = { "classpath:application.properties" })
public class BeansConfiguration {

	private Environment environment;
	@Autowired
	public void setEnvironment(Environment environment) {
	    this.environment = environment;
	}
	
	/**
	 * Contiene tutte le configurazini generali lette dal file application.properties
	 * @return classe GeneralConfig
	 * @author Alessandro Rossi
	 * @since 13 nov 2019
	 */
	@Bean(name="generalConfig")
	public GeneralConfig generalConfig(){
		GeneralConfig generalConfig = new GeneralConfig();
		return generalConfig;
	}

}