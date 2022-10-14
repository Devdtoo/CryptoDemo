package com.codebrew.cryptodemo.domain.repository

import com.codebrew.cryptodemo.common.Result
import com.codebrew.cryptodemo.data.model.response.CryptoResponse

interface EmptyStateRepository {
    suspend fun getEmptyStateData(): Result<CryptoResponse>
}