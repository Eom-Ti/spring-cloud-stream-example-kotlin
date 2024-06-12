package com.example.coin.store.mapstore

import com.example.coin.client.CoinApiClient
import com.example.coin.client.data.CoinMarketData
import com.example.coin.store.MarketCodeStore
import com.example.com.example.logger
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

@Component
class MarketCodeMapStore(
    private val mapStore: ConcurrentMap<String, String> = ConcurrentHashMap(),
    private val coinApiClient: CoinApiClient
) : MarketCodeStore, InitializingBean {

    val log = logger()

    override fun put(coinMarketData: CoinMarketData) {
        mapStore[coinMarketData.marketName] = coinMarketData.marketCode
    }

    override fun findAll(): List<String> {
        return mapStore.values.toList()
    }

    override fun refresh() {
        log.info("Refreshing MarketCodeMapStore")
        mapStore.clear()

        val coinMarketCode = coinApiClient.getMarketCodeList()
        coinMarketCode.forEach { mapStore[it.marketName] = it.marketCode }
    }

    override fun afterPropertiesSet() {
        refresh()
    }
}
