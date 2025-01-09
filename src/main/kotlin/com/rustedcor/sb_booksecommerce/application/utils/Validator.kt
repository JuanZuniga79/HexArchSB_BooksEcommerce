package com.rustedcor.sb_booksecommerce.application.utils

interface Validator {
    suspend fun validatePassword(password: String, encryptedPassword: String): Boolean
}