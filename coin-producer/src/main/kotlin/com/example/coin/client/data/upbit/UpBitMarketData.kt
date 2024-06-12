package com.example.coin.client.data.upbit

import com.example.coin.client.data.CoinMarketData
import com.fasterxml.jackson.annotation.JsonProperty

data class UpBitMarketData(

    @field:JsonProperty("market")
    override val marketCode: String,

    @field:JsonProperty("english_name")
    override val marketName: String
) : CoinMarketData
