package com.example.coin.batch

import com.example.coin.client.CoinApiClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Supplier

@Configuration
class CoinDataSupplier(
    private val coinApiClient: CoinApiClient
) {

    @Bean
    fun createCoinDataSupplier(): Supplier<String> {
        return Supplier {
            val marketCodes = coinApiClient.getMarketCodeList()
            println(marketCodes)

            println("@@@@@@@@@@@")

            "test"
        }

    }

}
