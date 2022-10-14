package com.codebrew.cryptodemo.domain.use_cases

import com.codebrew.cryptodemo.domain.repository.EmptyStateRepository
import javax.inject.Inject

class EmptyStateUseCase @Inject constructor(private val emptyStateRepository: EmptyStateRepository) {
    suspend operator fun invoke() = emptyStateRepository.getEmptyStateData()
}