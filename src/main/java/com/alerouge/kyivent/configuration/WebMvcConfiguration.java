package com.alerouge.kyivent.configuration;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.alerouge.kyivent.interceptor.CommonAttributesInterceptor;


/**
 * Classe di configurazione per spring mvc
 * @author Alessandro Rossi
 * @since 10 nov 2019
 * @version 2.0
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	
    @Autowired
    CommonAttributesInterceptor commonAttributesInterceptor;
    
    @Bean
	public LocaleResolver localeResolver() {
	    SessionLocaleResolver slr = new SessionLocaleResolver();
	    slr.setDefaultLocale(Locale.ITALIAN);
	    return slr;
	}
    
    @Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
	    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
	    lci.setParamName("lang");
	    return lci;
	}
    
    /**
     * Gestione interceptor
     * @author Alessandro Rossi
	 * @since 10 nov 2019
     */
    @Bean
    public CommonAttributesInterceptor commonAttributesInterceptor() {
        return new CommonAttributesInterceptor();
    }

    /**
     * Gestione esclusione path per interceptor
     * @author Alessandro Rossi
	 * @since 10 nov 2019
     */
    public @Override void addInterceptors(InterceptorRegistry registry) {
    	
    	registry.addInterceptor(localeChangeInterceptor());
    	
        registry.addInterceptor(commonAttributesInterceptor)
        	.excludePathPatterns("/css/**")
        	.excludePathPatterns("/js/**")
			.excludePathPatterns("/images/**");
    }
    
}