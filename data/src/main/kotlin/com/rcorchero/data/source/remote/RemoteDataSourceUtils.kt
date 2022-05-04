package com.rcorchero.data.source.remote

import arrow.core.Either
import com.rcorchero.domain.responses.ServerError
import retrofit2.Call

interface RemoteDataSourceUtils {

    fun <T, R> request(call: Call<T>, transform: (T) -> R): Either<ServerError, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> response.body()?.run { Either.Right(transform(this)) }
                    ?: Either.Left(ServerError)
                false -> Either.Left(ServerError)
            }
        } catch (exception: Throwable) {
            Either.Left(ServerError)
        }
    }
}