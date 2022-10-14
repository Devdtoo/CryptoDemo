package com.codebrew.cryptodemo.domain.use_cases

import com.codebrew.cryptodemo.domain.repository.ValueStateRepository
import javax.inject.Inject

class ValueStateUseCase @Inject constructor(private val valueStateRepository: ValueStateRepository) {
    suspend operator fun invoke() = valueStateRepository.getValueStateData()
}