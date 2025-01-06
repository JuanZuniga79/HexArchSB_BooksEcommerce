package com.rustedcor.sb_booksecommerce.infrastructure.components

import kotlinx.coroutines.reactor.awaitSingleOrNull
import kotlinx.coroutines.reactor.mono
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class JwtAuthenticationManager (
    private val jwtSupport: JwtSupport,
    private val users: ReactiveUserDetailsService
) : ReactiveAuthenticationManager {

    override fun authenticate(authentication: Authentication?): Mono<Authentication> {
        return Mono.justOrEmpty(authentication)
            .filter { auth -> auth is BearerToken}
            .cast(BearerToken::class.java)
            .flatMap { jwt ->  mono { validateToken(jwt) } }
            .onErrorMap { error -> InvalidBearerToken(error.message?: "Invalid Bearer Token") }
    }

    private suspend fun validateToken(token: BearerToken): Authentication {
        val username = jwtSupport.getUserName(token)
        val user = users.findByUsername(username).awaitSingleOrNull()
            ?: throw UsernameNotFoundException("User not found")
        if(jwtSupport.isValid(token, user)) {
            return UsernamePasswordAuthenticationToken(user.username, user.password, user.authorities)
        }
        throw IllegalArgumentException("Invalid token")
    }

}

class InvalidBearerToken(message: String) : AuthenticationException(message)