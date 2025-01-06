package com.rustedcor.sb_booksecommerce

import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.io.Encoders
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SbBooksEcommerceApplicationTests {

	@Test
	fun generateJWTs() {
		for (i in 1..10){
			val key = Keys.secretKeyFor(SignatureAlgorithm.HS256)
			println(Encoders.BASE64.encode(key.encoded))
		}
	}

}
