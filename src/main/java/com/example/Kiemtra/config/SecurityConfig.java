package com.example.Kiemtra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // 1. Trang /courses cho tất cả người dùng
                        .requestMatchers("/", "/home", "/courses", "/register/**", "/login", "/css/**", "/js/**").permitAll()

                        // 2. Trang /admin/** chỉ ADMIN truy cập (Dùng hasAuthority cho đúng đề)
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")

                        // 3. Trang /enroll/** chỉ STUDENT truy cập
                        .requestMatchers("/enroll/**").hasAuthority("STUDENT")

                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());

        return http.build();
    }
}