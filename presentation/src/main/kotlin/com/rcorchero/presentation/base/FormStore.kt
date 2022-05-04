package com.rcorchero.presentation.base

import com.rcorchero.presentation.asynchrony.StoreState

interface FormStore<A : State> {
    fun init(): StoreState<A>
}