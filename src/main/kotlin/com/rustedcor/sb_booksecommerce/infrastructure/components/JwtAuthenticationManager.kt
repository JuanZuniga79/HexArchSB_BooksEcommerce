package com.rustedcor.sb_booksecommerce.infrastructure.components

import com.rustedcor.sb_booksecommerce.infrastructure.adapters.output.repositories.UserRepository
import com.rustedcor.sb_booksecommerce.infrastructure.models.BearerToken
import kotlinx.coroutines.reactor.mono
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono

@Component
class JwtAuthenticationManager (
    private val jwtSupport: JwtSupport,
    private val userRepository: UserRepository
) : ReactiveAuthenticationManager {

    override fun authenticate(authentication: Authentication?): Mono<Authentication> {
        return Mono.justOrEmpty(authentication)
            .filter { auth -> auth is BearerToken}
            .cast(BearerToken::class.java)
            .flatMap { jwt ->  mono { validateToken(jwt) } }
            .onErrorMap { error ->
                when (error) {
                    is ResponseStatusException -> error
                    is IllegalArgumentException -> ResponseStatusException(HttpStatus.UNAUTHORIZED, error.message)
                    else -> ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Authentication error: $error")
                }
            }
    }

    private fun validateToken(token: BearerToken): Authentication {
        val username = jwtSupport.getUserName(token)
        val user = userRepository.getUserByUsername(username)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        if(jwtSupport.isValid(token)) {
            val authorities = listOf(SimpleGrantedAuthority("ROLE_USER"))
            return UsernamePasswordAuthenticationToken(
                user.username,
                null,
                authorities
            )
        }
        throw IllegalArgumentException("Invalid token")
    }

}