package com.codebrew.cryptodemo.di

import android.os.Build
import com.codebrew.cryptodemo.common.Constants
import com.codebrew.cryptodemo.data.remote.ApiService
import com.codebrew.cryptodemo.data.repo.EmptyStateRepositoryImpl
import com.codebrew.cryptodemo.data.repo.ValueStateRepositoryImpl
import com.codebrew.cryptodemo.domain.repository.EmptyStateRepository
import com.codebrew.cryptodemo.domain.repository.ValueStateRepository
import com.codebrew.cryptodemo.domain.use_cases.EmptyStateUseCase
import com.codebrew.cryptodemo.domain.use_cases.ValueStateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofitClient(): ApiService {
        return Retrofit
            .Builder()
            .client(
                OkHttpClient
                    .Builder()
                    .addInterceptor(HttpLoggingInterceptor())
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.baseUrl)
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideEmptyStateRepository(apiService: ApiService)
            : EmptyStateRepository = EmptyStateRepositoryImpl(apiService)

    @Singleton
    @Provides
    fun provideValueStateRepository(apiService: ApiService)
            : ValueStateRepository = ValueStateRepositoryImpl(apiService)

    @Singleton
    @Provides
    fun provideValueStateUseCase(valueStateRepositoryImpl: ValueStateRepositoryImpl)
            : ValueStateUseCase = ValueStateUseCase(valueStateRepositoryImpl)

    @Singleton
    @Provides
    fun provideEmptyStateUseCase(emptyStateRepositoryImpl: EmptyStateRepositoryImpl)
            : EmptyStateUseCase = EmptyStateUseCase(emptyStateRepositoryImpl)


}