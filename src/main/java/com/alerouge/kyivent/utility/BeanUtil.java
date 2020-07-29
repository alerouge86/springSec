package com.alerouge.kyivent.utility;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Classe utilizzata per ricavare i bean tramite il context
 */
@Component
public class BeanUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        synchronized (this) {
            if (BeanUtil.applicationContext == null) {
            	BeanUtil.applicationContext = applicationContext;
            }
        }
    }

    public static <T> T getBean(Class<T> classe) {
        return applicationContext.getBean(classe);
    }

    public static <T> T getBean(String qualifier, Class<T> classe) {
        return applicationContext.getBean(qualifier, classe);
    }

}