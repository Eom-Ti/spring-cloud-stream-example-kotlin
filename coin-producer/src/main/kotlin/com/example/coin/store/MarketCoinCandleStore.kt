package com.example.coin.store

import com.example.coin.client.data.CoinMarketData

interface MarketCoinCandleStore {

    fun findAll(): List<String>

    fun put(coinMarketData: CoinMarketData)

    fun refresh(coinMarketDataList: List<CoinMarketData>)

    fun isEmpty(): Boolean

    fun get(coinMarketName: String): String
}
