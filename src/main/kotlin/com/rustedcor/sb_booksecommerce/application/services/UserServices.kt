package com.rustedcor.sb_booksecommerce.application.services

import com.rustedcor.sb_booksecommerce.application.mappers.UserMapper
import com.rustedcor.sb_booksecommerce.application.mappers.impl.UserMapperImpl.toBasicUserDTO
import com.rustedcor.sb_booksecommerce.application.mappers.impl.UserMapperImpl.toUser
import com.rustedcor.sb_booksecommerce.application.utils.Generator
import com.rustedcor.sb_booksecommerce.application.utils.Validator
import com.rustedcor.sb_booksecommerce.infrastructure.adapters.output.repositories.UserRepository
import com.rustedcor.sb_booksecommerce.infrastructure.dto.input.CreateUserDTO
import com.rustedcor.sb_booksecommerce.infrastructure.dto.input.LoginDTO
import com.rustedcor.sb_booksecommerce.infrastructure.dto.output.ResponseLoginDTO
import com.rustedcor.sb_booksecommerce.infrastructure.entities.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class UserServices(
    private val generator: Generator,
    private val validator: Validator,
    private val repository: UserRepository,
    private val roleServices: RoleServices,
    private val authServices: AuthServices
) {

    private val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    suspend fun validateUsernameOrEmail(data: String): User?{
        return withContext(Dispatchers.IO){
            repository.getUserByUsername(data) ?: repository.getUserByEmail(data)
        }
    }

    suspend fun getUserByUsername(username: String): User? {
        return withContext(Dispatchers.IO){
            repository.getUserByUsername(username)
        }
    }

    suspend fun fillUserData(user: CreateUserDTO, roleName: String): User{
        val code = generator.generateHexCode(8)
        user.userName = "${user.firstName.replace(" ","").lowercase()}$code"
        val auxPass = user.password
        val password = generator.generateEncryptedPassword(auxPass);
        val date = LocalDate.parse(user.birthDate, dateFormatter)
        var role = roleServices.findRole(roleName)
        if(role == null){
            role = roleServices.createRole(roleName, null);
        }
        return user.toUser(date, role, password)
    }

    suspend fun login(user: LoginDTO): ResponseLoginDTO{
        val dbUser = validateUsernameOrEmail(user.username);
        dbUser ?: throw RuntimeException("Username or email invalid");
        if(!validator.validatePassword(user.password, dbUser.password)) throw RuntimeException("Password invalid");
        val token = authServices.getToken(dbUser.username);
        return ResponseLoginDTO(
            user=dbUser.toBasicUserDTO(),
            token=token
        )
    }

    suspend fun createUser(user: CreateUserDTO) {
        val newUser = fillUserData(user, "client")
        withContext(Dispatchers.IO){
            repository.save(newUser);
        }
    }

}