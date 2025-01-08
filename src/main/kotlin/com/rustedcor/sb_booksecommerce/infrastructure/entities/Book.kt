package com.rustedcor.sb_booksecommerce.infrastructure.entities

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "BOOKS")
data class Book(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    var name: String,
    var coverUrl: String? = null,
    var edition: String? = null,
    var description: String? = null,
)
