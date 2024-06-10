package com.example.coin.batch

import com.example.coin.client.CoinApiClient
import org.springframework.context.annotation.Configuration

@Configuration
class CoinDataSupplier(

    private val coinApiClient: CoinApiClient
) {


}
