package com.rustedcor.sb_booksecommerce.application.services

import com.rustedcor.sb_booksecommerce.infrastructure.components.JwtSupport
import org.springframework.stereotype.Service

@Service
class AuthServices (private val jwtSupport: JwtSupport){

    suspend fun getToken(username: String): String {
        return jwtSupport.generate(username).value;
    }

}