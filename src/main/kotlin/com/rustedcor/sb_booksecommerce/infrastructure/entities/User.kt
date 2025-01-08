package com.rustedcor.sb_booksecommerce.infrastructure.entities

import jakarta.persistence.*
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "USERS", uniqueConstraints = [UniqueConstraint(columnNames = ["userName", "email"])])
data class User(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    var firstName: String,
    var lastName: String,
    var username: String,
    var email: String,
    var password: String,
    var phoneNumber: String,
    var birthDate: LocalDate,
    var active: Boolean,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId", nullable = false)
    var role: Role? = null,
)
