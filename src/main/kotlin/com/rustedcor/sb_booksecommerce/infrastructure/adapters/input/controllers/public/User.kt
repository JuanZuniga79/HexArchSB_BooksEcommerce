package com.rustedcor.sb_booksecommerce.infrastructure.adapters.input.controllers.public

import com.rustedcor.sb_booksecommerce.infrastructure.dto.input.CreateUserDTO
import io.micrometer.core.ipc.http.HttpSender.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/public/user")
class User {

    @PostMapping("/createUser")
    suspend fun createUser(@RequestBody user: CreateUserDTO): ResponseEntity<CreateUserDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(user)
    }

}