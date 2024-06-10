package com.example.coin.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.DefaultResponseErrorHandler
import org.springframework.web.client.RestClient

@Configuration
class RestClientConfiguration {

    @Bean
    fun restClient(): RestClient {
        return RestClient.builder()
            .requestFactory(HttpComponentsClientHttpRequestFactory())
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultStatusHandler(DefaultResponseErrorHandler())
            .build()
    }
}
