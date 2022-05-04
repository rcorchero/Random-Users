package com.rcorchero.data.source.remote

import arrow.core.Either
import com.rcorchero.data.entities.remote.UserDto
import com.rcorchero.domain.responses.ServerError

class UserRemoteDataSourceImpl(
    private val service: UserService
) : UserRemoteDataSource, RemoteDataSourceUtils {

    override suspend fun getUsers(): Either<ServerError, List<UserDto>> =
        request(
            call = service.getUsers(),
            transform = { it }
        )

    override suspend fun getUser(userId: Int): Either<ServerError, UserDto> =
        request(
            call = service.getUser(userId = userId),
            transform = { it }
        )
}