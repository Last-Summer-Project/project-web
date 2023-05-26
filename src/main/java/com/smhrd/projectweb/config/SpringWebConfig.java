package com.smhrd.projectweb.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan({"com.smhrd.projectweb", "com.smhrd.projectweb.security"})
public class SpringWebConfig implements WebMvcConfigurer, ApplicationContextAware {

    private static final String UTF_8 = "UTF-8";
    private ApplicationContext applicationContext;

    public SpringWebConfig() {
        super();
    }


    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        return new CharacterEncodingFilter(UTF_8, true);
    }
}