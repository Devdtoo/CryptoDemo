package com.codebrew.cryptodemo.data.repo

import com.codebrew.cryptodemo.common.Result
import com.codebrew.cryptodemo.data.model.response.CryptoResponse
import com.codebrew.cryptodemo.data.remote.ApiService
import com.codebrew.cryptodemo.domain.repository.EmptyStateRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.Flow
import javax.inject.Inject

class EmptyStateRepositoryImpl @Inject constructor(
    val apiService: ApiService
) : EmptyStateRepository {

    override suspend fun getEmptyStateData(): Result<CryptoResponse> {
        return try {
            val response = apiService.getEmptyStateApiData()
            if (response.isSuccessful && response.body() != null) Result.Success(response.body()!!)
            else {
                Result.Error(message = "Something went wrong.")
            }
        } catch (e: HttpException) {
            Result.Error(message = e.message?: "Something went wrong.")
        } catch (e: IOException) {
            Result.Error(message = e.message?: "Please check your internet and retry")
        } catch (e: Exception) {
            Result.Error(message = e.message?: "Something went wrong.")
        }
    }
}