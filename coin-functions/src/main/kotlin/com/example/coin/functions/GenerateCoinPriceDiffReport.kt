package com.example.com.example.com.example.coin.functions

import com.example.com.example.com.example.coin.data.log.CoinPriceDiff

object GenerateCoinPriceDiffReport {

    private const val BLUE_DECREASE_SYMBOL: String = "\u001B[34m\u25BC\u001B[0m"
    private const val RED_INCREASE_SYMBOL: String = "\u001B[31m\u25B2\u001B[0m"
    private const val MARKET_FIELD: String = "MARKET : "
    private const val DIFF_FIELD: String = "DIFF : "
    private const val TIME_FIELD: String = "TIME : "
    private const val SAME_SYMBOL: String = "-"
    private const val SPACE: String = " "

    fun generateCoinPriceDiffReport(coinPriceDiffs: List<CoinPriceDiff>): String {
        val stringBuilder = StringBuilder()

        for (coinPriceDiff in coinPriceDiffs) {
            stringBuilder.append("\n")
                .append(getSymbol(coinPriceDiff))
                .append(SPACE)
                .append(MARKET_FIELD)
                .append(coinPriceDiff.market)
                .append(SPACE)
                .append(DIFF_FIELD)
                .append(coinPriceDiff.diff)
                .append(SPACE)
                .append(TIME_FIELD)
                .append(coinPriceDiff.candleTime)
        }
        return stringBuilder.toString()
    }

    private fun getSymbol(coinPriceDiff: CoinPriceDiff): String {
        return if (coinPriceDiff.isSame()) {
            SAME_SYMBOL
        } else if (coinPriceDiff.isIncrease()) {
            RED_INCREASE_SYMBOL
        } else {
            BLUE_DECREASE_SYMBOL
        }
    }
}
