package com.codebrew.cryptodemo.domain.use_cases

import android.util.Log
import com.codebrew.cryptodemo.common.Result
import com.codebrew.cryptodemo.data.model.response.CryptoResponse
import com.codebrew.cryptodemo.domain.repository.EmptyStateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class EmptyStateUseCase @Inject constructor(
    private val emptyStateRepository: EmptyStateRepository
) {
    operator fun invoke(): Flow<Result<CryptoResponse>> = flow {
        try {
            emit(Result.Loading<CryptoResponse>())
            val response = emptyStateRepository.getEmptyStateData()
            Log.d("API_LOGGER", response.toString())
            if (response.isSuccessful && response.body() != null) {
                Log.d("API_LOGGER", "Success")
                emit(Result.Success<CryptoResponse>(response.body()!!))
            } else {
                Log.d("API_LOGGER", "Failed ${response.errorBody()}")
                emit(Result.Error<CryptoResponse>(message = "Something went wrong."))
            }
        } catch (e: HttpException) {
            Log.d("API_LOGGER", "HttpException}")
            emit(Result.Error<CryptoResponse>(message = e.localizedMessage ?: "Something went wrong."))
        } catch (e: IOException) {
            Log.d("API_LOGGER", "IOException}")
            emit(Result.Error<CryptoResponse>(message = e.message ?: "Please check your internet and retry"))
        }
    }
}