package com.alerouge.kyivent.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@PropertySource(value = { "classpath:application.properties" })
public class BeansConfiguration {

	private Environment environment;
	@Autowired
	public void setEnvironment(Environment environment) {
	    this.environment = environment;
	}
	
	@Bean(name="generalConfig")
	public GeneralConfig generalConfig(){
		GeneralConfig generalConfig = new GeneralConfig();
//		generalConfig.setApplicationName(environment.getRequiredProperty("application.name"));
//		generalConfig.setApplicationVersion(environment.getRequiredProperty("application.version"));
//		generalConfig.setApplicationCity(environment.getRequiredProperty("application.city"));
//		generalConfig.setApplicationCityId(Long.parseLong(environment.getRequiredProperty("application.city.id")));
		return generalConfig;
	}

}