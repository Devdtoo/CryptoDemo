package com.codebrew.cryptodemo.data.repo

import com.codebrew.cryptodemo.common.Result
import com.codebrew.cryptodemo.data.model.response.CryptoResponse
import com.codebrew.cryptodemo.data.remote.ApiService
import com.codebrew.cryptodemo.domain.repository.EmptyStateRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.Flow
import javax.inject.Inject

class EmptyStateRepositoryImpl @Inject constructor(
    val apiService: ApiService
) : EmptyStateRepository {

    override suspend fun getEmptyStateData(): Response<CryptoResponse> = apiService.getEmptyStateApiData()
}