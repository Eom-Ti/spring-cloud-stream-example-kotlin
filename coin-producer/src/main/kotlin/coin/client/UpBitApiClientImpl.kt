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

    override fun getMarketCodeList(): List<String> {
        if (!::marketCodes.isInitialized) {
            val requestUri = upBitProperties.marketCodeRequestUri()
            log.info("[UpbitApiClientImpl.getMarketCodes]requestUri: $requestUri")

            val stringMarketCodes = restClient.get()
                .uri(requestUri)
                .retrieve()
                .body<List<UpBitMarketCode>>()

            log.info("[UpbitApiClientImpl.getMarketCode] marketCodes: $stringMarketCodes")

            marketCodes = stringMarketCodes
                ?.filter { it.market.startsWith("KRW") }
                ?.map { it.market }
                ?.toList() ?: listOf()
        }

        return marketCodes
    }
}
