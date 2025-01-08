package com.rustedcor.sb_booksecommerce.infrastructure.entities

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "EDITORIAL_BOOKS")
data class EditorialBooks(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "editorialId")
    var editorial: Editorial,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId")
    var book: Book
)
