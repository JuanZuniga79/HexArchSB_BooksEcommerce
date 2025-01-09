package com.rustedcor.sb_booksecommerce.infrastructure.dto.input

data class CreateUserDTO(
    val firstName: String,
    val lastName: String,
    var userName: String? = null,
    val email: String,
    var password: String,
    var birthDate: String,
)
