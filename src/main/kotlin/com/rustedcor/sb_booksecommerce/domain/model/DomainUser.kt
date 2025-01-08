package com.rustedcor.sb_booksecommerce.domain.model

import java.time.LocalDate

data class DomainUser(
    var firstName: String,
    var lastName: String,
    var userName: String? = null,
    var email: String,
    var password: String,
    var phoneNumber: String? = null,
    var birthDate: LocalDate,
    var active: Boolean? = null,
    var roleName: String,
    var address: String? = null,
    var additionalInfo: String? = null,
    var cityName: String? = null,
    var postalCode: String? = null,
    var countryCode: String? = null,
    var countryName: String? = null,
)
