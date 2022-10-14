package com.codebrew.cryptodemo.di

import com.codebrew.cryptodemo.data.repo.EmptyStateRepositoryImpl
import com.codebrew.cryptodemo.data.repo.ValueStateRepositoryImpl
import com.codebrew.cryptodemo.domain.repository.EmptyStateRepository
import com.codebrew.cryptodemo.domain.repository.ValueStateRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/*
@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun bindEmptyStateRepository(emptyStateRepositoryImpl: EmptyStateRepositoryImpl): EmptyStateRepository

    @Binds
    abstract fun bindValueStateRepository(valueStateRepositoryImpl: ValueStateRepositoryImpl): ValueStateRepository
}*/
