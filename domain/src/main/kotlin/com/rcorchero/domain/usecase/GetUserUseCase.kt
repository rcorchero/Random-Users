package com.rcorchero.domain.usecase

import arrow.core.Either
import com.rcorchero.domain.model.User
import com.rcorchero.domain.repository.UserRepository
import com.rcorchero.domain.responses.Failure
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository
) {

    suspend operator fun invoke(userId: Int): Either<Failure, User> =
        repository.getUser(userId = userId)
}