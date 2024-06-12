package com.example.coin.client.data.upbit

import com.example.coin.client.data.CoinMarketData

data class UpBitMarketData(
    override val marketCode: String,
    override val marketName: String
) : CoinMarketData
