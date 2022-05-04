package com.rcorchero.domain.usecase

import arrow.core.Either
import com.rcorchero.domain.model.User
import com.rcorchero.domain.repository.UserRepository
import com.rcorchero.domain.responses.Failure
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {

    suspend operator fun invoke(): Either<Failure, List<User>> =
        repository.getUsers()
}