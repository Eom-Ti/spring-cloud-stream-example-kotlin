package com.example.com.example.com.example.coin.data.message

import java.io.Serializable
import java.math.BigDecimal
import java.time.ZonedDateTime

class CandleDetail(
    val market: String,

    val price: BigDecimal,

    val candleTime: ZonedDateTime,

): Serializable {

    fun calcPriceDiff(candleDetail: CandleDetail): BigDecimal {
        require(market == candleDetail.market) {"market is not same with parameter market"}
        return price.subtract(candleDetail.price)
    }
}
