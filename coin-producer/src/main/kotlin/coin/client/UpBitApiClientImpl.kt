package com.example.coin.client

import com.example.coin.client.data.upbit.UpBitMarketCode
import com.example.coin.client.properties.UpBitProperties
import com.example.logger
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import org.springframework.web.client.body

@Component
class UpBitApiClientImpl(

    private val upBitProperties: UpBitProperties,
    private val restClient: RestClient,

    ) : CoinApiClient {

    lateinit var marketCodes: List<String>
    val log = logger()

    override fun getCandlesByMinute(marketCode: String): String {
        return "test"
    }

    override fun getMarketCodes(): List<String> {
        if (marketCodes.isNotEmpty()) {
            return marketCodes
        }

        val requestUri = upBitProperties.marketCodeRequestUri()
        log.info("[UpbitApiClientImpl.getMarketCodes]requestUri: $requestUri")

        val stringMarketCodes = restClient.get()
            .uri(requestUri)
            .retrieve()
            .body<List<UpBitMarketCode>>()

        log.info("[UpbitApiClientImpl.getMarketCode] marketCodes: $stringMarketCodes")

        marketCodes = stringMarketCodes
            ?.filter { it.marketCode.startsWith("KRW") }
            ?.map { it.marketCode }
            ?.toList() ?: listOf()

        return marketCodes
    }
}
