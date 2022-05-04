package com.rcorchero.presentation.base

import com.rcorchero.presentation.asynchrony.MutableStoreState
import com.rcorchero.presentation.asynchrony.StoreState

abstract class Store<A : Action, B : State>(initialState: B) :
    FormStore<B>,
    Reducer<A, B>,
    ActionHandler<A>,
    SideEffects<A, B> {

    private val state: MutableStoreState<B> = MutableStoreState(value = initialState)
    private val actions: MutableList<A> = mutableListOf()

    override fun getState(): StoreState<B> = state

    override fun getActions(): List<A> = actions

    override fun init(): StoreState<B> {
        onInit()
        return state
    }

    override fun A.handle() {
        actions.add(this)
        val newState: B = reduce(currentState = state.value)
        newState.state()
        sideEffects(currentState = newState)
    }

    abstract fun onInit()

    fun onCreate(view: ViewStore<B>) {
        val state: StoreState<B> = init()
        view.run { state.render() }
    }

    private fun B.state() {
        state.value = this
    }
}