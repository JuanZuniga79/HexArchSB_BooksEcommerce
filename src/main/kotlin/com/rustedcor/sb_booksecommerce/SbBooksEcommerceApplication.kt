package com.rustedcor.sb_booksecommerce

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class SbBooksEcommerceApplication

fun main(args: Array<String>) {
	runApplication<SbBooksEcommerceApplication>(*args)
}
