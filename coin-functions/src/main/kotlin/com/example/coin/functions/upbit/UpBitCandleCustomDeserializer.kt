package com.example.com.example.com.example.coin.functions.upbit

import com.example.coin.data.message.Candle
import com.example.com.example.com.example.coin.data.message.CandleDetail
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object UpBitCandleCustomDeserializer: JsonDeserializer<List<Candle>>() {

    private const val MARKET_FIELD = "market";
    private const val PRICE = "opening_price";
    private const val CANDLE_TIME = "candle_date_time_utc";

    override fun deserialize(jasonParser: JsonParser, deserializationContext: DeserializationContext): List<Candle> {

        val candles = ArrayList<Candle>()

        while (!jasonParser.isClosed) {
            // if rootNode is null continue
            val rootNode = jasonParser.readValueAsTree<JsonNode>() ?: continue

            val candleDetails = rootNode
                .map {
                    val market = it[MARKET_FIELD].asText()
                    val price = BigDecimal(it[PRICE].asText()).setScale(6, RoundingMode.HALF_UP)
                    val candleTime =
                        LocalDateTime.parse(it[CANDLE_TIME].asText(), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                            .atZone(ZoneOffset.UTC)

                    CandleDetail(market, price, candleTime)
                }.toList()
            candles.add(Candle(candleDetails))
        }
        return candles
    }
}
