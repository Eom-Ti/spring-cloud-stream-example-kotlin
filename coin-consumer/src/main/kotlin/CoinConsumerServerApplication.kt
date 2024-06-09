package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CoinConsumerServerApplication

fun main(args: Array<String>) {
    runApplication<CoinConsumerServerApplication>(*args)
}
