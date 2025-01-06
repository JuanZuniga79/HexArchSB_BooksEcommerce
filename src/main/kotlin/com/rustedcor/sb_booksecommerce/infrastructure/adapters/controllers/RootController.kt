package com.rustedcor.sb_booksecommerce.infrastructure.adapters.controllers

import com.rustedcor.sb_booksecommerce.infrastructure.components.JwtSupport
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.boot.actuate.web.exchanges.HttpExchange.Principal
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
class RootController (
    private val encoder: PasswordEncoder,
    private val users: ReactiveUserDetailsService,
    private val jwtSupport: JwtSupport
) {

    @GetMapping("")
    suspend fun hello(): String {
        return "Hello World"
    }

    @PostMapping("/api/public/login")
    suspend fun login(@RequestBody login: Login): Jwt {
        val user = users.findByUsername(login.username).awaitSingleOrNull()
        user?.let {
            if(encoder.matches(login.password, it.password)){
                return Jwt(jwtSupport.generate(it.username).value)
            }
        }
        throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
    }
}

data class Jwt(val token: String)
data class Login(val username: String, val password: String)
data class Profile(val username: String)