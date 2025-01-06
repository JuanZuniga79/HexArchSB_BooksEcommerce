package com.rustedcor.sb_booksecommerce.infrastructure.configuration

import com.rustedcor.sb_booksecommerce.infrastructure.components.JwtAuthenticationManager
import com.rustedcor.sb_booksecommerce.infrastructure.components.JwtServerAuthenticationConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter

@EnableWebFluxSecurity
@Configuration
class SecurityConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder();

    @Bean
    fun userDetailsService(encoder: PasswordEncoder): MapReactiveUserDetailsService {
        val user = User.builder()
            .username("Kenji")
            .password(encoder.encode("password"))
            .roles("USER")
            .build()

        return MapReactiveUserDetailsService(user);
    }

    @Bean
    fun sprintSecurityFilterChain(
        converter: JwtServerAuthenticationConverter,
        http: ServerHttpSecurity,
        authManager: JwtAuthenticationManager
    ): SecurityWebFilterChain {

        val filter = AuthenticationWebFilter(authManager);
        filter.setServerAuthenticationConverter(converter)

        http
            .cors {
                cors -> cors.disable()
            }
            .csrf {it.disable()}
            .authorizeExchange { auth ->
                auth.pathMatchers("/api/public/**").permitAll()
                    .anyExchange().authenticated()
            }
            .addFilterAt(filter, SecurityWebFiltersOrder.AUTHENTICATION)
            .httpBasic {it.disable()}
            .formLogin {it.disable()}
        return http.build();
    }

}