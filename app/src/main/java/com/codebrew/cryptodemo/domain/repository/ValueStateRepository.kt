package com.codebrew.cryptodemo.domain.repository

import com.codebrew.cryptodemo.common.Result
import com.codebrew.cryptodemo.data.model.response.CryptoResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ValueStateRepository {
    suspend fun getValueStateData(): Response<CryptoResponse>
}