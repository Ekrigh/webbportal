package com.group6.webbportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource datasource) {
        return new JdbcUserDetailsManager(datasource);
    }

    @Bean
    public SecurityFilterChain securityFilterchain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/v1/customers").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/customers").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/customers/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/customers/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
        );
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
