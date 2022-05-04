package com.rcorchero.data.source

import arrow.core.Either
import com.rcorchero.data.entities.remote.toDomain
import com.rcorchero.data.source.remote.UserRemoteDataSource
import com.rcorchero.domain.model.User
import com.rcorchero.domain.repository.UserRepository
import com.rcorchero.domain.responses.Failure

class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun getUsers(): Either<Failure, List<User>> =
        remoteDataSource.getUsers()
            .map { it.toDomain() }

    override suspend fun getUser(userId: Int): Either<Failure, User> =
        remoteDataSource.getUser(userId = userId)
            .map { it.toDomain() }
}