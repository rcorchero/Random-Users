package com.rcorchero.data.source.remote

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class UserServiceTest {

    private lateinit var userService: UserService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        userService = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(UserService::class.java)
    }

    private fun enqueueMockResponse(fileName: String) {
        javaClass.classLoader?.let {
            val inputStream = it.getResourceAsStream(fileName)
            val source = inputStream.source().buffer()
            val mockResponse = MockResponse()
            mockResponse.setBody(source.readString(Charsets.UTF_8))
            server.enqueue(mockResponse)
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `getUsers should have correct path`(): Unit = runBlocking {
        // Given
        enqueueMockResponse("users.json")

        // When
        val responseBody = userService.getUsers().execute().body()
        val request = server.takeRequest()

        // Then
        assert(responseBody != null)
        assert(request.path == "/users")
    }

    @Test
    fun `getUsers should be a GET method`(): Unit = runBlocking {
        // Given
        enqueueMockResponse("users.json")

        // When
        val responseBody = userService.getUsers().execute().body()
        val request = server.takeRequest()

        // Then
        assert(responseBody != null)
        assert(request.method == "GET")
    }

    @Test
    fun `getUser should have correct path`(): Unit = runBlocking {
        // Given
        enqueueMockResponse("user.json")

        // When
        val responseBody = userService.getUser(userId = 1).execute().body()
        val request = server.takeRequest()

        // Then
        assert(responseBody != null)
        assert(request.path == "/users/1")
    }

    @Test
    fun `getUser should be a GET method`(): Unit = runBlocking {
        // Given
        enqueueMockResponse("user.json")

        // When
        val responseBody = userService.getUser(userId = 1).execute().body()
        val request = server.takeRequest()

        // Then
        assert(responseBody != null)
        assert(request.method == "GET")
    }
}