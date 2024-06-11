package com.example.coin.client.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

@ConfigurationProperties(value = "coin.upbit.api")
class UpBitProperties(
    private val baseUrl: String,
    private val marketCodeUrl: String,
    private val candlesMinutesUrl: String
) {
    fun marketCodeRequestUri(): URI {
        return baseUriBuilder()
            .path(marketCodeUrl)
            .build().toUri()
    }

    fun candlesMinutesRequestUri(marketCode: String): URI {
        return baseUriBuilder()
            .path(candlesMinutesUrl)
            .queryParam("market", marketCode)
            .build().toUri()
    }

    private fun baseUriBuilder(): UriComponentsBuilder {
        return UriComponentsBuilder.fromHttpUrl(baseUrl)
    }
}
