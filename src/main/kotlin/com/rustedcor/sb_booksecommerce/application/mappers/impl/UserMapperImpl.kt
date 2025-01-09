package com.rustedcor.sb_booksecommerce.application.mappers.impl

import com.rustedcor.sb_booksecommerce.application.mappers.UserMapper
import com.rustedcor.sb_booksecommerce.infrastructure.dto.input.CreateUserDTO
import com.rustedcor.sb_booksecommerce.infrastructure.dto.output.ResponseBasicUserDTO
import com.rustedcor.sb_booksecommerce.infrastructure.entities.Role
import com.rustedcor.sb_booksecommerce.infrastructure.entities.User
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
object UserMapperImpl : UserMapper{

    override suspend fun CreateUserDTO.toUser(date: LocalDate, role: Role, pass: String): User = User(
        null, firstName, lastName,
        userName?: throw IllegalArgumentException("Username has not been generated"),
        email, pass, null, birthDate = date, active = true, role
    )

    override suspend fun User.toBasicUserDTO() = ResponseBasicUserDTO(firstName, lastName, username);

}