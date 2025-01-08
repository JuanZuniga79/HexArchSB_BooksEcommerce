package com.rustedcor.sb_booksecommerce.infrastructure.entities

import jakarta.persistence.*

@Entity
@Table(name = "CITIES")
data class City(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "countryId", nullable = false)
    val country: Country,
)
