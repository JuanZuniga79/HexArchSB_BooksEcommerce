package com.rustedcor.sb_booksecommerce.infrastructure.adapters.output.repositories

import com.rustedcor.sb_booksecommerce.infrastructure.entities.Role
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoleRepository : CrudRepository<Role, Int> {
    fun findByName(name: String): Role?
}