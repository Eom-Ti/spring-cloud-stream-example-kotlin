package com.example.com.example.coin.upbit

import com.example.coin.data.message.Candle
import com.example.coin.functions.TransformCoinCandleData
import com.example.com.example.com.example.coin.functions.enums.ExchangeType
import com.example.com.example.logger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import java.util.function.Function

@Configuration
class UpBitCoinTransformer {

    companion object {
        private const val HEADER_KEY = "your-header-key"
        private const val MESSAGE_KEY = "coin-price"
    }

    var log = logger()

    @Bean
    fun transformUpBitCoinData(): Function<String, Message<List<Candle>>> {
        return Function { rawData ->
            log.info("Transforming upBit coin data rawData : $rawData")
            val candles = TransformCoinCandleData.toData(rawData, ExchangeType.UP_BIT)
            log.info("Transforming upBit coin data completed : $candles")

            MessageBuilder.withPayload(candles)
                .setHeader(HEADER_KEY, MESSAGE_KEY)
                .build()
        }
    }
}
