package com.example.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // H2 Console用にCSRF無効化
            .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**").disable())

            // H2 Console用にframeOptionsをsameOriginに
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))

            // APIとH2 Consoleを許可
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**").permitAll() // ← API を全て許可
                .requestMatchers("/h2-console/**").permitAll()
                .anyRequest().permitAll()               // ← 他も許可
            )
            .formLogin(login -> login.disable())        // ← ログイン画面を無効化
            .httpBasic(basic -> basic.disable());       // ← Basic認証も無効化
        
        return http.build();
    }
}
