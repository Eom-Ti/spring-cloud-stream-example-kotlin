package com.example.coin.store.liststore

import com.example.coin.client.CoinApiClient
import com.example.coin.store.MarketCodeStore
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

@Component
class MarketCodeMapStore(
    private val mapStore: ConcurrentMap<String, String> = ConcurrentHashMap(),
    private val coinApiClient: CoinApiClient
) : MarketCodeStore, InitializingBean {

    override fun put(marketCode: String) {
        mapStore.put(marketCode, )
    }

    override fun get(): List<String> {
        return listStore.toList()
    }

    override fun refresh() {
        listStore.clear()
        coinApiClient.getMarketCodeList()
    }

    override fun afterPropertiesSet() {
        refresh()
    }
}