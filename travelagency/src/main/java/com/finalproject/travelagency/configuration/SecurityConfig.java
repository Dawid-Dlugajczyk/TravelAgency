package com.finalproject.travelagency.configuration;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import java.util.Arrays;
import java.util.List;

import static com.finalproject.travelagency.model.Role.ADMIN;
import static com.finalproject.travelagency.model.Role.USER;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableWebMvc
public class SecurityConfig{

    private JwtAuthenticationFilter jwtAuthFilter;
    private AuthenticationProvider authenticationProvider;
    @Autowired
    public SecurityConfig(AuthenticationProvider authenticationProvider, JwtAuthenticationFilter jwtAuthFilter) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthFilter = jwtAuthFilter;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{


        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200")); // Add your frontend URL
        config.setAllowedMethods(List.of("*")); // Allow all methods (GET, POST, etc.)
        config.setAllowedHeaders(List.of("*")); // Allow all headers
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        // Apply CORS configuration to HttpSecurity
        http
                .cors().configurationSource(request -> config)
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/users").hasAuthority(ADMIN.name())
                .requestMatchers(HttpMethod.POST,  "/api/v1/auth/tours/add", "/api/v1/auth/tours/update").hasAuthority(ADMIN.name())
                .requestMatchers(HttpMethod.GET, "/api/v1/auth/user","/api/v1/auth/user/find" ).hasAnyAuthority(USER.name(), ADMIN.name())
                .requestMatchers(HttpMethod.GET, "/api/v1/auth/tours/**","/api/v1/auth/users").permitAll()
                .requestMatchers("/api/v1/auth/register","/api/v1/auth/authenticate").permitAll()
                //.requestMatchers(HttpMethod.GET, "/api/v1/auth/tours","/api/v1/auth/users" ).permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .cors();


        /*http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers( "/api/v1/auth/register", "/api/v1/auth/authenticate") //list
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .enableSessionUrlRewriting(true)
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);*/


        return http.build();
    }

}
