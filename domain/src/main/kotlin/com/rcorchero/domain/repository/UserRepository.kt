package com.rcorchero.domain.repository

import arrow.core.Either
import com.rcorchero.domain.model.User
import com.rcorchero.domain.responses.Failure

interface UserRepository {

    suspend fun getUsers(): Either<Failure, List<User>>

    suspend fun getUser(userId: Int): Either<Failure, User>
}