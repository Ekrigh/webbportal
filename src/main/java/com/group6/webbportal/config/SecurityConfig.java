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
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/v1/customers").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/customers").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/customers/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/customers/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/courts").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/courts/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/padel/bookings/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/padel/bookings").hasRole("ADMIN")

                        //Sushi
                        .requestMatchers(HttpMethod.GET, "/api/v1/sushis").hasAnyRole("USER","ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/sushis/admin").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/sushis/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/sushis/user").hasRole("USER")

                        .requestMatchers(HttpMethod.POST, "/api/v1/sushi/bookings").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/sushi/bookings/{id}").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/sushi/bookings/{id}").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/sushi/rooms/{id}").hasRole("ADMIN")

                        //Cinema
                        .requestMatchers(HttpMethod.PUT, "/api/v1/cinema/rooms/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/movies").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/movies/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/movies").hasAnyRole("ADMIN","USER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/cinema/bookings/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/cinema/bookings").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/cinema/bookings/**").hasRole("USER")
                        .anyRequest().authenticated()
        );
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}
