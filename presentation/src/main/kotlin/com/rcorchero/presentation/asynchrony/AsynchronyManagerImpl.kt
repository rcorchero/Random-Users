package com.rcorchero.presentation.asynchrony

import arrow.core.Either
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class AsynchronyManagerImpl(
    override val coroutineContext: CoroutineContext,
    private val io: CoroutineContext
) : AsynchronyManager, CoroutineScope {

    override fun <A, B> launch(
        function: suspend () -> Either<A, B>,
        error: (A) -> Unit,
        success: (B) -> Unit
    ) {
        launch {
            io { function() }.fold(ifLeft = { error(it) }, ifRight = { success(it) })
        }
    }

    override fun <A> launchMain(
        function: suspend () -> A
    ) {
        launch { function() }
    }

    override fun <A> launchIO(
        function: suspend () -> A
    ) {
        launch(io) { function() }
    }

    private suspend fun <T> io(block: suspend CoroutineScope.() -> T): T =
        withContext(io) { block() }

    override fun cancel() = coroutineContext.cancelChildren()
}


typealias StoreState<A> = StateFlow<A>
typealias MutableStoreState<A> = MutableStateFlow<A>
fun <T> MutableStoreState(value: T) = MutableStateFlow(value)