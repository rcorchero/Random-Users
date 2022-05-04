package com.rcorchero.data.source.remote

import arrow.core.Either
import com.rcorchero.data.entities.remote.UserDto
import com.rcorchero.domain.responses.ServerError

interface UserRemoteDataSource {

    suspend fun getUsers(): Either<ServerError, List<UserDto>>

    suspend fun getUser(userId: Int): Either<ServerError, UserDto>
}