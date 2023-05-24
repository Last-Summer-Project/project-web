package com.smhrd.projectweb.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter("UTF-8", true);

        http.addFilterBefore(filter, CsrfFilter.class);
        http.cors();
        http.csrf().ignoringAntMatchers("/api/**");
        http.authorizeRequests(authorize ->
                authorize
                        // Index
                        .antMatchers("/api/v1", "/api/v1/").permitAll()
                        // Authenticate
                        .antMatchers("/api/v1/device/authenticate").permitAll()
                        .anyRequest().authenticated()
        );
        http.exceptionHandling().accessDeniedPage("/authenticate");
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                    .antMatchers("/authenticate")
                    .antMatchers("/dashboard")
                    .antMatchers("/static/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
