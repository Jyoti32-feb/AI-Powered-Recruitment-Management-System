
package com.jyoti.ai_recruitment_management.config;

import com.jyoti.ai_recruitment_management.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http.csrf(csrf -> csrf.disable())

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/auth/register",
                                "/api/auth/login"
                        ).permitAll()

                        .requestMatchers(
                                org.springframework.http.HttpMethod.GET,
                                "/api/jobs/open-jobs"
                        ).permitAll()

                        .requestMatchers("/api/auth/hello").hasRole("RECRUITER")

                        .requestMatchers(
                                org.springframework.http.HttpMethod.POST,
                                "/api/jobs"
                        ).hasRole("RECRUITER")

                        .requestMatchers(
                                org.springframework.http.HttpMethod.GET,
                                "/api/jobs/*"
                        ).permitAll()

                        .requestMatchers(
                                org.springframework.http.HttpMethod.PUT,
                                "/api/jobs/*"
                        ).hasRole("RECRUITER")

                        .requestMatchers(HttpMethod.PATCH, "/api/jobs/*/close")
                        .hasRole("RECRUITER")

                        .requestMatchers(HttpMethod.POST, "/api/candidate/profile")
                        .hasRole("CANDIDATE")

                        .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(
                                new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)
                        )
                )
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}
