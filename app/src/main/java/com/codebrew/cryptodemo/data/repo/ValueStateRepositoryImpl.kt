package com.codebrew.cryptodemo.data.repo

import com.codebrew.cryptodemo.common.Result
import com.codebrew.cryptodemo.data.model.response.CryptoResponse
import com.codebrew.cryptodemo.data.remote.ApiService
import com.codebrew.cryptodemo.domain.repository.ValueStateRepository
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class ValueStateRepositoryImpl @Inject constructor(
    val apiService: ApiService
) : ValueStateRepository {

    override suspend fun getValueStateData(): Response<CryptoResponse> = apiService.getValueStateApiData()

}