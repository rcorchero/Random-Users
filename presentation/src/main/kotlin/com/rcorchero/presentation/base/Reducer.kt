package com.rcorchero.presentation.base

interface Reducer<A : Action, B : State> {
    fun A.reduce(currentState: B): B
}