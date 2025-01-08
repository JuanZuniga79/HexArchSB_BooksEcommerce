package com.rustedcor.sb_booksecommerce.infrastructure.entities

import jakarta.persistence.*

@Entity
@Table(name = "AUTHORS")
data class Author(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var firstName: String,
    var lastName: String,
    var stageName: String? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "countryId")
    var city: City,
)
