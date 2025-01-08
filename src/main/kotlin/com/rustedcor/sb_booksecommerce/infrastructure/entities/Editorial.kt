package com.rustedcor.sb_booksecommerce.infrastructure.entities

import jakarta.persistence.*

@Entity
@Table(name = "EDITORIALS")
data class Editorial(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addressId")
    var address: Address,
)
