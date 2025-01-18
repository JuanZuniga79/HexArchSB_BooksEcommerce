package com.rustedcor.sb_booksecommerce.infrastructure.dto.output

import com.rustedcor.sb_booksecommerce.infrastructure.entities.Role

data class ResponseBasicUserDTO(
    val firstName: String,
    val lastName: String,
    val username: String,
)
