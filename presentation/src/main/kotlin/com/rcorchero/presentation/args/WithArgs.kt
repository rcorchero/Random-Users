package com.fintonic.presentation.common.args

import com.rcorchero.presentation.args.Args

interface WithArgs<A : Args> {
    val getArgs: A

    fun <A> getArgs(f: () -> A): Lazy<A> =
        lazy { f() }
}