package com.example.coin.batch

import com.example.coin.service.CoinDataScrapService
import kotlinx.coroutines.runBlocking
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import java.nio.charset.StandardCharsets
import java.util.function.Supplier

@Configuration
class CoinDataSupplier(
    private val coinDataScrapService: CoinDataScrapService
) {
    companion object {
        const val MESSAGE_KEY = "coinRawData"
    }

    @Bean
    fun createCoinDataSupplier(): Supplier<Message<String>> {
        return Supplier {
            val stringCoinCandle = runBlocking {
                coinDataScrapService.scrapMarketCoinMinuteCandle()
            }

            MessageBuilder
                .withPayload(stringCoinCandle)
                .setHeader(KafkaHeaders.KEY, MESSAGE_KEY.toByteArray(StandardCharsets.UTF_8))
                .build()
        }
    }
}
