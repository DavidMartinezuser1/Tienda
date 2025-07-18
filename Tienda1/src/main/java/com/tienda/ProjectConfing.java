package com.tienda;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class ProjectConfing implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver(){
       var slr = new SessionLocaleResolver();
       slr.setDefaultLocale(Locale.getDefault());
       slr.setLocaleAttributeName("session.current.locale");
       slr.setTimeZoneAttributeName("session.current.timezone");
       return slr;
    }
    
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
      var lci = new LocaleChangeInterceptor();
      lci.setParamName("lang");
      return lci;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registro){
        registro.addInterceptor(localeChangeInterceptor());
    }
    
    @Bean("messageSource")
    public MessageSource messageSource(){
        ResourceBundleMessageSource MessageSource = new ResourceBundleMessageSource();
        MessageSource.setBasenames("messages");
        MessageSource.setDefaultEncoding("UTF-8");
        return MessageSource;
    }
}
