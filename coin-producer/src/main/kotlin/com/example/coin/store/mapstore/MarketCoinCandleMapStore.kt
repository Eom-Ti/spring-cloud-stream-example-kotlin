package com.example.coin.store.mapstore

import com.example.coin.client.data.CoinMarketData
import com.example.coin.store.MarketCoinCandleStore
import com.example.com.example.logger
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class MarketCoinCandleMapStore : MarketCoinCandleStore {
    private val mapStore: ConcurrentHashMap<String, String> = ConcurrentHashMap()
    val log = logger()

    override fun get(coinMarketName: String): String {
        return mapStore.getOrElse(coinMarketName) {
            throw NoSuchElementException("Key $coinMarketName not found")
        }
    }

    override fun put(coinMarketData: CoinMarketData) {
        mapStore[coinMarketData.marketName] = coinMarketData.marketCode
    }

    override fun findAll(): List<String> {
        return mapStore.values.toList()
    }

    override fun refresh(coinMarketDataList: List<CoinMarketData>) {
        log.info("Refreshing MarketCodeMapStore")
        mapStore.clear()
        coinMarketDataList.forEach { mapStore[it.marketName] = it.marketCode }
    }

    override fun isEmpty(): Boolean {
        return mapStore.isEmpty()
    }
}
