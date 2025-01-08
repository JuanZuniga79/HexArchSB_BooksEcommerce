package com.rustedcor.sb_booksecommerce.infrastructure.models

import java.util.UUID

data class JwtPayload(
    val id: UUID,
    val username: String,
    val roleName: String,
)
