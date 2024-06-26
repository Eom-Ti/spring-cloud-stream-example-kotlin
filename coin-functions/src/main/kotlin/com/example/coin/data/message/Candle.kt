package com.example.coin.data.message

import com.example.com.example.com.example.coin.data.message.CandleDetail
import java.io.Serializable

class Candle(private val candleDetails: List<CandleDetail>): Serializable
