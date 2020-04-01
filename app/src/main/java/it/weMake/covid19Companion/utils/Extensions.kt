package it.weMake.covid19Companion.utils

import java.text.DecimalFormat

fun Int.numberWithCommas(): String {
    val formatter = DecimalFormat("#,###,###")
    return formatter.format(this.toDouble())
}