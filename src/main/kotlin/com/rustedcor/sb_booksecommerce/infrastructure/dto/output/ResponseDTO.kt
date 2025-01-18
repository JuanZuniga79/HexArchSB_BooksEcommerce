package com.rustedcor.sb_booksecommerce.infrastructure.dto.output

data class ResponseDTO<T>(
    val message : String,
    val status : Boolean,
    val data : T,
)
