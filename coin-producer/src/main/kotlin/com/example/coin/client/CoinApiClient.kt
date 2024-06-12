package com.example.coin.client

import com.example.coin.client.data.CoinMarketData

interface CoinApiClient {

    /**
     * Gets coin price information in fractions.
     *
     * @param marketCode marketCode ex)KRW-ETC
     * @return  String Type CandleData
     */
    fun getCandlesByMinute(marketCode: String): String

    /**
     * Gets the market code provided by the current exchange.
     *
     * @return List<String> marketCodes
     */
    fun getMarketCodeList(): List<CoinMarketData>

}
