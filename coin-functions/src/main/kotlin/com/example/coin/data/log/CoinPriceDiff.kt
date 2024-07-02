package com.example.com.example.com.example.coin.data.log

import java.math.BigDecimal
import java.math.RoundingMode
import java.time.ZonedDateTime

class CoinPriceDiff(

    val market: String,

    val diff: BigDecimal,

    val candleTime: ZonedDateTime
) {

    fun isIncrease(): Boolean {
        return diff > BigDecimal.ZERO
    }

    fun isSame(): Boolean {
        return diff == BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)
    }
}
