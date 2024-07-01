package com.example.com.example.coin.cache

import com.example.coin.data.message.Candle
import com.example.com.example.com.example.coin.data.message.CandleDetail
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class RedisCoinCacheService(
    private val redisTemplate: RedisTemplate<String, Any>,
    private val objectMapper: ObjectMapper = jacksonObjectMapper()

): CoinCacheService {

    override fun put(candles: List<Candle>) {
        candles.forEach { candle ->
            candle.candleDetails.forEach {
            run {
                val market = it.market
                redisTemplate.opsForValue()[market] = candle
                redisTemplate.expire(market, 70000, TimeUnit.MILLISECONDS)
            }
        }}
    }

    override fun get(candleDetail: CandleDetail): CandleDetail {
        val cacheResult = redisTemplate.opsForValue()[candleDetail.market] ?: return candleDetail
        return objectMapper.convertValue(cacheResult, CandleDetail::class.java)
    }
}
