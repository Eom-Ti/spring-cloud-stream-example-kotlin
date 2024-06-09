package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CoinProducerApplication

fun main(args: Array<String>) {
    runApplication<CoinProducerApplication>(*args)
}
