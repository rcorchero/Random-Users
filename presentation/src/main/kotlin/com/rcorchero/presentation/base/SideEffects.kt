package com.rcorchero.presentation.base

import com.rcorchero.presentation.asynchrony.StoreState

interface SideEffects<A : Action, B : State> {
    fun A.sideEffects(currentState: B)
    fun getActions(): List<A>
    fun getState(): StoreState<B>
}