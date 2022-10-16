package com.codebrew.cryptodemo.domain.repository

import com.codebrew.cryptodemo.common.Result
import com.codebrew.cryptodemo.data.model.response.CryptoResponse
import retrofit2.Response

interface EmptyStateRepository {
    suspend fun getEmptyStateData(): Response<CryptoResponse>
}