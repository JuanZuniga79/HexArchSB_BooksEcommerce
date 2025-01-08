package com.rustedcor.sb_booksecommerce.infrastructure.entities

import jakarta.persistence.*

@Entity
@Table(name = "COUNTRIES", uniqueConstraints = [UniqueConstraint(columnNames = ["name", "code"])])
data class Country(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    var name: String,
    @Column(length = 4)
    var code: String,
)
