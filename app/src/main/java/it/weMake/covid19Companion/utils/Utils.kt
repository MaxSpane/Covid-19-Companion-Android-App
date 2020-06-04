package it.weMake.covid19Companion.utils

import android.content.Context

fun getFlagResourceId(context: Context, iso2: String): Int? =
    getDrawableResourceId(context, "flag_${iso2.toLowerCase()}")

fun getDrawableResourceId(context: Context, resourceName: String): Int? =
    try {
        context.resources.getIdentifier(resourceName, "drawable", context.packageName)
    }catch (e: Exception){
        null
    }