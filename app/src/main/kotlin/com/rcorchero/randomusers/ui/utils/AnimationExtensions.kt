package com.rcorchero.randomusers.ui.utils

import android.app.Activity
import com.rcorchero.randomusers.R

fun Activity.animateOutToLeft() =
    overridePendingTransition(
        R.anim.slide_in_left,
        R.anim.slide_out_right
    )

fun Activity.animateInToRight() =
    overridePendingTransition(
        R.anim.slide_in_right,
        R.anim.slide_out_left
    )