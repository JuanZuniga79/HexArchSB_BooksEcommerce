package com.rustedcor.sb_booksecommerce.application.services

import com.rustedcor.sb_booksecommerce.infrastructure.adapters.output.repositories.RoleRepository
import com.rustedcor.sb_booksecommerce.infrastructure.entities.Role
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class RoleServices(private val roleRepository: RoleRepository) {

    suspend fun findRole(name: String): Role? = withContext(Dispatchers.IO){
        roleRepository.findByName(name);
    }

    suspend fun createRole(name: String, description: String?): Role{
        val role = Role(name = name)
        return withContext(Dispatchers.IO){
            roleRepository.save(role);
        }
    }

}