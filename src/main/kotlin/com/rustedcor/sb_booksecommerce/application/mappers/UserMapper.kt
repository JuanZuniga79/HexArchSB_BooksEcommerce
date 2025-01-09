package com.rustedcor.sb_booksecommerce.application.mappers

import com.rustedcor.sb_booksecommerce.infrastructure.dto.input.CreateUserDTO
import com.rustedcor.sb_booksecommerce.infrastructure.dto.output.ResponseBasicUserDTO
import com.rustedcor.sb_booksecommerce.infrastructure.entities.Role
import com.rustedcor.sb_booksecommerce.infrastructure.entities.User
import java.time.LocalDate

interface UserMapper {
    suspend fun CreateUserDTO.toUser(date: LocalDate, role: Role, pass: String): User;
    suspend fun User.toBasicUserDTO(): ResponseBasicUserDTO;
}