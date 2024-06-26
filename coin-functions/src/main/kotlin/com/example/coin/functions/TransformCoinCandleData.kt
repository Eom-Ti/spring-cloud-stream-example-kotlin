package com.example.coin.functions

import com.example.coin.data.message.Candle
import com.example.com.example.com.example.coin.functions.enums.ExchangeType
import com.example.com.example.logger
import com.fasterxml.jackson.module.kotlin.readValue

object TransformCoinCandleData {

    private val log = logger()

    fun toData(rawData: String, exchangeType: ExchangeType): List<Candle> {
        log.info("[TransformCoinData.toData] exchangeType : $exchangeType, rawData: $rawData")
        return exchangeType.objectMapper.readValue<List<Candle>>(rawData)
    }
}
