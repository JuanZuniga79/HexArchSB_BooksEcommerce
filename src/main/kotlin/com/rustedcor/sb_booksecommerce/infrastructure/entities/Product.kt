package com.rustedcor.sb_booksecommerce.infrastructure.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "PRODUCTS")
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    var stock: Int = 0,
    var discount: Double? = null,
    var price: Double,
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId", nullable = false)
    var book: Book,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sellerId", nullable = false)
    var seller: User
)
