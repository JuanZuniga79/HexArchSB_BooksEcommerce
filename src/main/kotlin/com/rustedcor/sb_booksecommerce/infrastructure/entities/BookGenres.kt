package com.rustedcor.sb_booksecommerce.infrastructure.entities

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "BOOK_GENRES")
data class BookGenres(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId", nullable = false)
    var book: Book,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genreId", nullable = false)
    var genre: Genre,
)
