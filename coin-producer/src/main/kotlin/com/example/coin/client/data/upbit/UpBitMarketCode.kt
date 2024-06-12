package com.example.coin.client.data.upbit

import com.example.coin.client.data.CoinMarketCode

data class UpBitMarketCode(
    override val marketCode: String,
    override val marketName: String
) : CoinMarketCode
