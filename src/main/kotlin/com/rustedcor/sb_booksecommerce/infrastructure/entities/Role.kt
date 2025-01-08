package com.rustedcor.sb_booksecommerce.infrastructure.entities

import jakarta.persistence.*

@Entity
@Table(name = "ROLES", uniqueConstraints = [UniqueConstraint(columnNames = ["name"])])
data class Role(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @Column(length = 32)
    var name: String,
    @Column(nullable = true)
    var description: String? = null,
)
