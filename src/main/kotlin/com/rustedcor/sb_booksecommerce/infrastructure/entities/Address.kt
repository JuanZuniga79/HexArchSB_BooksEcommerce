package com.rustedcor.sb_booksecommerce.infrastructure.entities

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "ADDRESSES")
data class Address(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    var address: String? = null,
    var additionalInfo: String? = null,
    @Column(length = 8)
    var postalCode: String? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cityId", nullable = false)
    var city: City,
)
