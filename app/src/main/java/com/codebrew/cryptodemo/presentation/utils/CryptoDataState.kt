package com.codebrew.cryptodemo.presentation.utils

import com.codebrew.cryptodemo.data.model.response.CryptoResponse

data class CryptoDataState(
    val isLoading: Boolean = false,
    val cryptoData: CryptoResponse? = null,
    val error: String = ""
)
