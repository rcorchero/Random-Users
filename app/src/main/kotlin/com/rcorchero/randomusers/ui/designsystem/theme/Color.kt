package com.rcorchero.randomusers.ui.designsystem.theme

import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val DarkGrey = Color(0xFF191114)
val DarkGrey20 = Color(0x20191114)
val Yellow60 = Color(0x60FFFF00)
val Green60 = Color(0x6000FF00)
val Blue60 = Color(0x600000FF)
val Magenta60 = Color(0x60FF00FF)
val Red60 = Color(0x60FF0000)

fun getRandomColor(
    list: List<Color> = listOf(Yellow60, Green60, Blue60, Magenta60, Red60)
): Color = list.random()