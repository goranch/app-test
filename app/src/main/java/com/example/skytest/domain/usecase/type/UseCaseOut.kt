package com.example.skytest.domain.usecase.type

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class UseCaseOut<OUT> {
    operator fun invoke(): Flow<Result<OUT>> = flow {
        emit(
            try {
                Result.success(block())
            } catch (ex: Exception) {
                Result.failure(exception = ex)
            }
        )
    }

    protected abstract val block: suspend () -> OUT
}
