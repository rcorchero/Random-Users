package com.rcorchero.data.source.remote

import com.rcorchero.data.entities.remote.UserDto
import com.rcorchero.data.source.remote.UserService.Params.USER_ID
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {

    companion object Urls {
        private const val GET_USERS = "/users"
        private const val GET_USER = "/users/{$USER_ID}"
    }

    object Params {
        const val USER_ID = "userId"
    }

    @GET(GET_USERS)
    fun getUsers(): Call<List<UserDto>>

    @GET(GET_USER)
    fun getUser(@Path(USER_ID) userId: Int): Call<UserDto>
}