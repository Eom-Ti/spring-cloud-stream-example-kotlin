package com.example.coin.store

import com.example.coin.client.data.CoinMarketData

interface MarketCodeStore {

    fun findAll(): List<String>

    fun put(coinMarketData: CoinMarketData)

    fun refresh()
}
