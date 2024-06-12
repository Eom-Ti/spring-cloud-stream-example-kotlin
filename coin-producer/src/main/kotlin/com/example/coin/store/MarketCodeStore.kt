package com.example.coin.store

interface MarketCodeStore {

    fun get(): List<String>

    fun put(marketCode: String)

    fun refresh()
}