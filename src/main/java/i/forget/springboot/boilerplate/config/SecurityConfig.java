package i.forget.springboot.boilerplate.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.authorization.SingleResultAuthorizationManager.permitAll;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable for development/APIs
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Public endpoints
//                        .anyRequest().authenticated() // Everything else needs a login
                )
                .httpBasic(Customizer.withDefaults()); // Allows testing via Postman/Curl

        return http.build();
    }
}