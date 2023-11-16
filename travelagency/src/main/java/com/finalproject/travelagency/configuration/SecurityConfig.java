package com.finalproject.travelagency.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

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

        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/api/v1/auth/admin/**").hasAuthority(ADMIN.name())
                .requestMatchers("/api/v1/auth/user/**").hasAnyAuthority(USER.name(), ADMIN.name())
                .requestMatchers(HttpMethod.POST, "api/v1/auth/projects").hasAnyAuthority( "Admin")
                .requestMatchers(HttpMethod.POST, "/api/v1/auth/register","/api/v1/auth/authenticate" ).permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


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
