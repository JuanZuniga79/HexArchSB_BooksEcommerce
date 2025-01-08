package com.rustedcor.sb_booksecommerce.infrastructure.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "USER_ADDRESSES")
data class UserAddresses(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    var user: User,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addressId", nullable = false)
    var address: Address,
)
