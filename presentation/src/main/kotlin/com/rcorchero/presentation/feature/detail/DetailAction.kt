package com.rcorchero.presentation.feature.detail

import com.rcorchero.presentation.base.Action

sealed class DetailsAction : Action

object LoadDetails : DetailsAction()

data class OnSuccessLoadDetails(
    val model: DetailsModel
) : DetailsAction()
object OnErrorLoadDetails : DetailsAction()