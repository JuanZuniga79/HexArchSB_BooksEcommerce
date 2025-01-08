package com.rustedcor.sb_booksecommerce.infrastructure.dto.input

data class CreateUserDTO(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val birthDate: String,
)
