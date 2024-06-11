package com.example.com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class CoinProducerApplication

fun main(args: Array<String>) {
    runApplication<CoinProducerApplication>(*args)
}
