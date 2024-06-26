package com.example.com.example.com.example.coin.functions.enums

import com.example.com.example.com.example.coin.functions.upbit.UpBitCandleCustomDeserializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

enum class ExchangeType {

    UP_BIT {
        override fun createObjectMapper(): ObjectMapper {
            return jacksonObjectMapper().registerModule(SimpleModule()
                    .addDeserializer(List::class.javaObjectType, UpBitCandleCustomDeserializer)
                )
        }
    },
    BIT_THUMB {
        override fun createObjectMapper(): ObjectMapper {
            println("objectMapper BIT")
            return ObjectMapper()
        }
    };

    val objectMapper: ObjectMapper by lazy {
        createObjectMapper()
    }

    protected abstract fun createObjectMapper(): ObjectMapper
}
