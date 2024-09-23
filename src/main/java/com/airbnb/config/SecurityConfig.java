package com.airbnb.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {
    private final JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();

        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);
        http.authorizeHttpRequests()
                .requestMatchers("/app/v1/auth/createUser", "/app/v1/auth/createPropertyOwner", "/app/v1/auth/logIn", "/app/v1/city/addCity", "/app/v1/country/addCountry","/app/v1/property/propertyResult","/app/v1/auth","/app/v1/auth/getUserById","/app/v1/property/**","/api/v1/roomType/**","/api/phone/parse")
                .permitAll()
                .requestMatchers("/app/v1/property/addProperty","/api/v1/room").hasAnyRole("OWNER","MANAGER","ADMIN")
                .requestMatchers("/app/v1/auth/createPropertyManager","/app/v1/auth/deleteUser").hasRole("ADMIN")
                .requestMatchers("/app/v1/reviews/createReview","/app/v1/reviews/userReviews","/api/v1/booking/createBooking").hasRole("USER")
                .anyRequest().authenticated();

        return http.build();
    }
}
