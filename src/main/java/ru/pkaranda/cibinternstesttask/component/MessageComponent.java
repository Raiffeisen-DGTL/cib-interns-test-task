package ru.pkaranda.cibinternstesttask.component;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class MessageComponent {

    private final MessageSource messageSource;

    public String getMessage(String id) {
        Locale locale = LocaleContextHolder.getLocale();
        System.out.println(locale);
        return messageSource.getMessage(id, null, locale);
    }

    public String getMessageWithParams(String id, Object... params) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(id, params, locale);
    }
}
