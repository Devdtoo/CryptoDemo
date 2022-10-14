package com.codebrew.cryptodemo.data.model.response

data class CryptoPrice(
    val current_price_in_usd: String,
    val logo: String,
    val title: String
)