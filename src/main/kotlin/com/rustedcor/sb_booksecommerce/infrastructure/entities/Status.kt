package com.rustedcor.sb_booksecommerce.infrastructure.entities

import jakarta.persistence.*

@Entity
@Table(name = "STATUSES", uniqueConstraints = [UniqueConstraint(columnNames = ["name"])])
data class Status(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    var name: String,
    var description: String? = null,
)
