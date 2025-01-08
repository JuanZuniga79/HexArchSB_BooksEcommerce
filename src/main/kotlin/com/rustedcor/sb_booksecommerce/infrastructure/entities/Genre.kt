package com.rustedcor.sb_booksecommerce.infrastructure.entities

import jakarta.persistence.*

@Entity
@Table(name = "GENRES", uniqueConstraints = [UniqueConstraint(columnNames = ["name"])])
data class Genre(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String,
    var description: String? = null,
)
