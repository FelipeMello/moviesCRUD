package com.example.movie.util;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MessageUtil {

    @Qualifier(value = "messageSource")
    private final MessageSource messageSource;

    public MessageUtil(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String selector, Object... parameters) {
        return messageSource.getMessage(selector, parameters, LocaleContextHolder.getLocale());
    }
}