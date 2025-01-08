package com.rustedcor.sb_booksecommerce.infrastructure.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "PURCHASES")
data class Purchase(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    var buyer: User,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "statusId", nullable = false)
    var status: Status,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addressId", nullable = false)
    var deliveryAddress: Address,
)
