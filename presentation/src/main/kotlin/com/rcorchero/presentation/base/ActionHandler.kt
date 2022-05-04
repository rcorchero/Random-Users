package com.rcorchero.presentation.base

interface ActionHandler<A : Action> {
    fun A.handle()
}