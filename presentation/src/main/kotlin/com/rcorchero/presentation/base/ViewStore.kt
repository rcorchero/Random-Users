package com.rcorchero.presentation.base

import com.rcorchero.presentation.asynchrony.StoreState

interface ViewStore<B : State> {
    fun StoreState<B>.render()
}