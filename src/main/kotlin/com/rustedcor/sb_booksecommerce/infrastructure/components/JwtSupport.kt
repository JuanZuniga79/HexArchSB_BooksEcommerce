package com.rustedcor.sb_booksecommerce.infrastructure.components

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

class BearerToken(val value: String) : AbstractAuthenticationToken(AuthorityUtils.NO_AUTHORITIES){
    override fun getCredentials(): Any = value;

    override fun getPrincipal(): Any = value;
}

@Component
class JwtSupport {

    private val key = Keys.hmacShaKeyFor("9rSPQFeqLUhTtn9haBaWzPNMKiF6tVxkJhSd8sH7hyk=".toByteArray())
    private val parser = Jwts.parser().verifyWith(key).build();


    fun generate(username: String): BearerToken {
        val builder = Jwts.builder()
            .subject(username)
            .issuedAt(Date.from(Instant.now()))
            .expiration(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
            .signWith(key)
        return BearerToken(builder.compact())
    }

    fun getUserName(token: BearerToken): String {
        return parser.parseSignedClaims(token.value).payload.subject
    }

    fun isValid(token: BearerToken, user: UserDetails?): Boolean {
        val claims = parser.parseSignedClaims(token.value).payload
        val expired = claims.expiration.before(Date.from(Instant.now()))
        if(user == null) throw IllegalArgumentException("User cannot be null!")
        val res = !expired && (claims.subject == user.username)
        println("Res: $res")
        return res
    }

}