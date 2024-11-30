package group9.movie_app.configuration;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final AuthenticationProvider authenticationProvider;
    private final Filter jwtAuthFilter;

    @Bean

    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((request)-> request
                                .requestMatchers("/movies-app/login", "movies-app/register",
                                        "/movies-app/film", "/movies-app/series",
                                        "/movies-app/find/**", "/movies-app/movie/**",
                                        "/movies-app/reviews/**", "/movies-app/homepage",
                                        "/movies-app/top-reviews", "/movies-app/top-views",
                                        "/movies-app/top-wishlist", "/movies-app/top-recent-release").permitAll()
                                .requestMatchers("/movies-app/admin/**").hasAnyAuthority("ADMIN")
                                .requestMatchers("/movies-app/user/**").hasAnyAuthority("USER")
                                .requestMatchers("/movies-app/user-admin/**").hasAnyAuthority("ADMIN", "USER")
                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}

