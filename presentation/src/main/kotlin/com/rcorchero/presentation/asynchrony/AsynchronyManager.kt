package com.rcorchero.presentation.asynchrony

import arrow.core.Either

interface AsynchronyManager {

    fun <A, B> launch(
        function: suspend () -> Either<A, B>,
        error: (A) -> Unit = {},
        success: (B) -> Unit = {}
    )

    fun <A> launchMain(
        function: suspend () -> A
    )

    fun <A> launchIO(
        function: suspend () -> A
    )

    fun cancel()
}