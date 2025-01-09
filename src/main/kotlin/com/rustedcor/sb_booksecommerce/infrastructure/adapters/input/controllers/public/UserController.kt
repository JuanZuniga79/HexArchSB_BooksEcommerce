package com.rustedcor.sb_booksecommerce.infrastructure.adapters.input.controllers.public

import com.rustedcor.sb_booksecommerce.application.services.UserServices
import com.rustedcor.sb_booksecommerce.infrastructure.dto.input.CreateUserDTO
import com.rustedcor.sb_booksecommerce.infrastructure.dto.input.LoginDTO
import com.rustedcor.sb_booksecommerce.infrastructure.dto.output.ResponseLoginDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/public/user")
class UserController (private val service: UserServices) {

    @PostMapping("/createUser")
    suspend fun createUser(@RequestBody user: CreateUserDTO): ResponseEntity<CreateUserDTO> {
        service.createUser(user);
        return ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    suspend fun login(@RequestBody user: LoginDTO): ResponseEntity<ResponseLoginDTO>{
        val response = service.login(user);
        if(response === null){
            return ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

}