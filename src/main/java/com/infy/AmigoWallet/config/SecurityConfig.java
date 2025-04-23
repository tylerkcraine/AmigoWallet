package com.infy.AmigoWallet.config;

import com.infy.AmigoWallet.service.JwtUtil;
import com.infy.AmigoWallet.service.WalletUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private static final String[] SWAGGER_PATHS = {"/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**", "/webjars/swagger-ui/**"};

    @Autowired
    private JwtUtil jwtUtil;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration, PasswordEncoder passwordEncoder, WalletUserDetailsService wordUserService) throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(wordUserService);
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(a -> a.requestMatchers(SWAGGER_PATHS).permitAll())
                .authorizeHttpRequests(a -> a.requestMatchers("/h2-console/**").permitAll())
                .authorizeHttpRequests(a -> a.requestMatchers("/auth/register", "/auth/login").permitAll())
                .authorizeHttpRequests(a -> a.requestMatchers("/auth/role").hasRole("ADMIN"))
                .authorizeHttpRequests(a -> a.requestMatchers("/word/**").hasRole("ADMIN"))
                .authorizeHttpRequests(a -> a.requestMatchers(HttpMethod.POST, "/offer").hasRole("ADMIN"))
                .authorizeHttpRequests(a -> a.requestMatchers(HttpMethod.GET, "/offer").authenticated())
                .authorizeHttpRequests(a -> a.anyRequest().authenticated())
                .sessionManagement(a -> a.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
