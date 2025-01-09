package com.rustedcor.sb_booksecommerce.application.utils.impl

import com.rustedcor.sb_booksecommerce.application.utils.Generator
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.util.*

@Component
class GeneratorImpl() : Generator {

    private var bCrypt: BCryptPasswordEncoder = BCryptPasswordEncoder();

    override suspend fun generateHexCode(length: Int): String {
        val hexChars = "0123456789ABCDEF"
        val random = Random()
        return StringBuilder(length)
            .apply {
                repeat(length) { append(hexChars[random.nextInt(hexChars.length)]) }
            }
            .toString()
    }

    override suspend fun generateEncryptedPassword(password: String): String {
        return bCrypt.encode(password)
    }
}