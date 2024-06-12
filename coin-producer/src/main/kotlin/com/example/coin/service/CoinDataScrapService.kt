package com.example.coin.service

import com.example.coin.client.CoinApiClient
import com.example.coin.store.MarketCoinCandleStore
import com.example.com.example.logger
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Service

@Service
class CoinDataScrapService(

    private val coinApiClient: CoinApiClient,
    private val marketCoinCandleStore: MarketCoinCandleStore
) {
    val log = logger()

    suspend fun scrapMarketCoinMinuteCandle(): String = coroutineScope {

        if (marketCoinCandleStore.isEmpty()) {
            log.info("[CoinDataScrapService] marketCoinCandleStore refresh cause is empty")
            val filteringMarketCode = coinApiClient.getMarketCodeList()
                .filter { it.marketCode.startsWith("KRW") }

            marketCoinCandleStore.refresh(filteringMarketCode)
        }

        val marketCoinCodes = marketCoinCandleStore.findAll()
        val results = marketCoinCodes.map { coinCode ->
            async {
                coinApiClient.getCandlesByMinute(coinCode)
            }
        }.awaitAll().also {
            log.info("finished")
        }

        return@coroutineScope results.joinToString()
    }
}
