package com.rcorchero.domain.responses

sealed class Failure
object ServerError : Failure()
object RepositoryError : Failure()
