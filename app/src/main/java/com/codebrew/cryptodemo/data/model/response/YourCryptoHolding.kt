package com.codebrew.cryptodemo.data.model.response

data class YourCryptoHolding(
    val current_bal_in_token: String,
    val current_bal_in_usd: String,
    val logo: String,
    val title: String
)