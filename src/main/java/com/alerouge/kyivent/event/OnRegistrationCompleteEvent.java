package com.alerouge.kyivent.event;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.alerouge.kyivent.model.user.UserEntity;

@SuppressWarnings("serial")
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private final String appUrl;
    private final Locale locale;
    private final UserEntity user;

    public OnRegistrationCompleteEvent(final UserEntity user, final Locale locale, final String appUrl) {
        super(user);
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }

    //

    public String getAppUrl() {
        return appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public UserEntity getUser() {
        return user;
    }

}
