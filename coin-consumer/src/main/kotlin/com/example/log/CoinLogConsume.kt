package com.example.com.example.log

import com.example.coin.data.message.Candle
import com.example.com.example.coin.cache.CoinCacheService
import com.example.com.example.com.example.coin.data.log.CoinPriceDiff
import com.example.com.example.com.example.coin.functions.GenerateCoinPriceDiffReport
import com.example.com.example.logger
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Consumer
import java.util.function.Function

@Configuration
class CoinLogConsume(

    private val coinCacheService: CoinCacheService
) {

    val log = logger()

    @Bean
    fun generateReport(): Function<List<Candle>, String> {
        return Function { candles ->
            val coinPriceDiffs = candles.flatMap { candle ->
                candle.candleDetails.map { candleDetail ->
                    val cacheCandleDetail = coinCacheService.get(candleDetail)
                    val coinDiff = cacheCandleDetail.calcPriceDiff(candleDetail)

                    CoinPriceDiff(candleDetail.market, coinDiff, candleDetail.candleTime)
                }
            }.toList()

            coinCacheService.put(candles)
            return@Function GenerateCoinPriceDiffReport.generateCoinPriceDiffReport(coinPriceDiffs)
        }
    }

    @Bean
    @ConditionalOnProperty(value = ["spring.cloud.function.definition"], havingValue = "generateReport|log")
    fun log(): Consumer<String> = Consumer {
            logData -> log.info(logData)
     }
}
