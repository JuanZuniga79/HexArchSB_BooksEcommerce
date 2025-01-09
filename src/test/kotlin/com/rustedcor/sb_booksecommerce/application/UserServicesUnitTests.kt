package com.rustedcor.sb_booksecommerce.application

import com.rustedcor.sb_booksecommerce.application.services.UserServices
import com.rustedcor.sb_booksecommerce.infrastructure.dto.input.CreateUserDTO
import com.rustedcor.sb_booksecommerce.infrastructure.dto.input.LoginDTO
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServicesUnitTests(@Autowired private val userServices: UserServices) {

    @Test
    fun `should create an user and send welcome email`() {
        runBlocking {
            val user = CreateUserDTO(
                "John", "Doe", null, "john.doe@mail.com",
                "12345678", "01/01/2000"
            )
            val newUser = userServices.fillUserData(user, "client")
            Assertions.assertNotNull(newUser)
        }
    }

    @Test
    fun `should validate users login data and return token with basic user data`(){
        runBlocking {
            val user = LoginDTO("jczp.020401@gmail.com", "@KaiShin79")
            val response = userServices.login(user);
            Assertions.assertNotNull(response)
        }
    }

}