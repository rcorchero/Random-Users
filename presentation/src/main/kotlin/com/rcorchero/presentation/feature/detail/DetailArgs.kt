package com.rcorchero.presentation.feature.detail

import com.rcorchero.presentation.args.Args

data class DetailArgs(
    val userId: Int
) : Args {
    fun isValidUserId(): Boolean = userId != -1
}