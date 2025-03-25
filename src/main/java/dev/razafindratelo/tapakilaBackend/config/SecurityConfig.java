package dev.razafindratelo.tapakilaBackend.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenFilter tokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSec) throws Exception {
        return httpSec.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(HttpMethod.POST,"/user/sign-up").permitAll()
                                .requestMatchers(HttpMethod.PATCH, "/user/activate-account").permitAll()
                                .requestMatchers(HttpMethod.POST,"/user/sign-in").permitAll()
                                .requestMatchers(HttpMethod.POST,"/user/update-password").permitAll()
                                .requestMatchers(HttpMethod.GET, "/events").permitAll()
                                .requestMatchers(HttpMethod.GET, "/event/image/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/ping-pong").permitAll()
                                .requestMatchers(HttpMethod.GET, "/tickets-stats/**").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET, "/events-turnover").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET, "/events-turnover/**").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET, "/event-turnover").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET, "/events/user/**").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET, "/users").hasAuthority("ROLE_ADMIN")
                                .anyRequest().authenticated()
                )
                .sessionManagement(
                        configurer -> configurer
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider();

        daoAuthProvider.setUserDetailsService(userDetailsService);
        daoAuthProvider.setPasswordEncoder(passwordEncoder);

        return daoAuthProvider;
    }
}
