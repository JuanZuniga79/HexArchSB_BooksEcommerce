package com.rustedcor.sb_booksecommerce.application.utils

interface Generator {
    suspend fun generateHexCode(length: Int): String
    suspend fun generateEncryptedPassword(password: String): String
}