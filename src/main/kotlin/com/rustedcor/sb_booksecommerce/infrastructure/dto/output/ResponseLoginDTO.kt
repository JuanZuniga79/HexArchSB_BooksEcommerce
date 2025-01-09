package com.rustedcor.sb_booksecommerce.infrastructure.dto.output

data class ResponseLoginDTO(
    val user: ResponseBasicUserDTO,
    val token: String,
)
