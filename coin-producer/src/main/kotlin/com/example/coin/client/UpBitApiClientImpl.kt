package com.example.coin.client

import com.example.coin.client.data.CoinMarketData
import com.example.coin.client.data.upbit.UpBitMarketData
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
        val requestUri = upBitProperties.candlesMinutesRequestUri(marketCode)
        log.info("[UpBitApiClientImpl.getCandlesByMinute]requestUri: $requestUri")

        return restClient.get()
            .uri(requestUri)
            .retrieve()
            .body<String>() ?: throw IllegalArgumentException("Failed to retrieve data for market code: $marketCode")
    }

    override fun getMarketCodeList(): List<CoinMarketData> {
        val requestUri = upBitProperties.marketCodeRequestUri()
        log.info("[UpBitApiClientImpl.getMarketCodes]requestUri: $requestUri")

        val coinMarketCode = restClient.get()
            .uri(requestUri)
            .retrieve()
            .body<List<UpBitMarketData>>() ?: emptyList<UpBitMarketData>().run {
                log.info("[UpBitApiClientImpl.getMarketCodes] empty result")
            return this
        }

        return coinMarketCode.filter {
            it.marketCode.startsWith("KRW")
        }.toList()
    }
}
