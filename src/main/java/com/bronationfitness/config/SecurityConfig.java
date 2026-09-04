package com.bronationfitness.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {

        return new InMemoryUserDetailsManager(

            User.withUsername("admin")
                .password("{noop}admin123")
                .roles("ADMIN")
                .build()

        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http

            .authorizeHttpRequests(auth -> auth

                .requestMatchers(
                    new AntPathRequestMatcher("/login"),
                    new AntPathRequestMatcher("/css/**"),
                    new AntPathRequestMatcher("/js/**"),
                    new AntPathRequestMatcher("/images/**"),
                    new AntPathRequestMatcher("/h2-console/**")
                ).permitAll()

                .anyRequest().authenticated()
            )

            .formLogin(form -> form

                .loginPage("/login")

                .loginProcessingUrl("/login")

                .defaultSuccessUrl("/dashboard", true)

                .failureUrl("/login?error")

                .permitAll()
            )

            .logout(logout -> logout

                .logoutUrl("/logout")

                .logoutSuccessUrl("/login?logout")

                .invalidateHttpSession(true)

                .clearAuthentication(true)

                .deleteCookies("JSESSIONID")

                .permitAll()
            )

            .csrf(csrf -> csrf

                .ignoringRequestMatchers(
                    new AntPathRequestMatcher("/h2-console/**")
                )
            )

            .headers(headers -> headers

                .frameOptions(frame -> frame.sameOrigin())
            );

        return http.build();
    }
}