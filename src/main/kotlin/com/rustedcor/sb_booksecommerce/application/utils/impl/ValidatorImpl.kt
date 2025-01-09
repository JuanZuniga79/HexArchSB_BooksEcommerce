package com.rustedcor.sb_booksecommerce.application.utils.impl

import com.rustedcor.sb_booksecommerce.application.utils.Validator
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class ValidatorImpl : Validator{

    private var bCrypt: BCryptPasswordEncoder = BCryptPasswordEncoder();

    override suspend fun validatePassword(password: String, encryptedPassword: String): Boolean {
        return bCrypt.matches(password, encryptedPassword);
    }

}