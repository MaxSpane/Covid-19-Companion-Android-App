package it.weMake.covid19Companion.utils

import android.content.Context

fun getFlagResourceId(context: Context, iso2: String): Int? =
    try {
        context.resources.getIdentifier("flag_${iso2.toLowerCase()}", "drawable", context.packageName)
    }catch (e: Exception){
        null
    }