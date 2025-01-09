package com.rustedcor.sb_booksecommerce.infrastructure.adapters.output.repositories

import com.rustedcor.sb_booksecommerce.infrastructure.entities.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserRepository : CrudRepository<User, UUID>{
    fun getUserByUsername(username: String): User?
    fun getUserByEmail(email: String): User?
}