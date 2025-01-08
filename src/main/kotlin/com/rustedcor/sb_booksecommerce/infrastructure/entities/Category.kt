package com.rustedcor.sb_booksecommerce.infrastructure.entities

import jakarta.persistence.*

@Entity
@Table(name = "CATEGORIES", uniqueConstraints = [UniqueConstraint(columnNames = ["name"])])
data class Category(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String,
    var description: String? = null,
)
