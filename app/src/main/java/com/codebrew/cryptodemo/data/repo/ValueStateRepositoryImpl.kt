package com.codebrew.cryptodemo.data.repo

import com.codebrew.cryptodemo.common.Result
import com.codebrew.cryptodemo.data.model.response.CryptoResponse
import com.codebrew.cryptodemo.data.remote.ApiService
import com.codebrew.cryptodemo.domain.repository.ValueStateRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ValueStateRepositoryImpl @Inject constructor(
    val apiService: ApiService
) : ValueStateRepository {

    override suspend fun getValueStateData(): Result<CryptoResponse> {
        return try {
            val response = apiService.getValueStateApiData()
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