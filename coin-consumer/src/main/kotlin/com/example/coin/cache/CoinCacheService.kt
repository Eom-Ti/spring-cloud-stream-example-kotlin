package com.example.com.example.coin.cache

import com.example.coin.data.message.Candle
import com.example.com.example.com.example.coin.data.message.CandleDetail

interface CoinCacheService {

    /**
     * Performs the role of storing coin information in the cache.
     *
     * @param candles candleDetail List data
     */
    fun put(candles: List<Candle>)

    /**
     * Retrieves stored coin information from the cache. If it does not exist, returns the candleDetail entered.
     *
     * @param candleDetail  candleDetail data(market, price, time)
     * @return  CandleDetail
     */
    fun get(candleDetail: CandleDetail): CandleDetail
}
