package com.example.coin.client

import com.example.coin.client.data.CoinMarketCode
import com.example.coin.client.data.upbit.UpBitMarketCode
import com.example.coin.client.properties.UpBitProperties
import com.example.com.example.logger
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import org.springframework.web.client.body

@Component
class UpBitApiClientImpl(

    private val upBitProperties: UpBitProperties,
    private val restClient: RestClient,
) : CoinApiClient {

    val log = logger()

    override fun getCandlesByMinute(marketCode: String): String {
        return "test"
    }

    override fun getMarketCodeList(): List<CoinMarketCode> {
        val requestUri = upBitProperties.marketCodeRequestUri()
        log.info("[UpBitApiClientImpl.getMarketCodes]requestUri: $requestUri")

        return restClient.get()
            .uri(requestUri)
            .retrieve()
            .body<List<UpBitMarketCode>>() ?: emptyList<UpBitMarketCode>().run {
                log.info("[UpBitApiClientImpl.getMarketCodes] empty result")
            return this
        }
    }
}
