package it.wemake.covid19Companion.local.preference

import android.content.Context
import androidx.core.content.edit
import it.wemake.covid19Companion.local.utils.COVID_19_COMPANION_SHARED_PREFERENCES
import it.wemake.covid19Companion.local.utils.NUMBER_OF_CHECKS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Covid19CompanionSharedPreferences constructor(
    context: Context
) {

    private val covid19CompanionAppSharedPref = context.getSharedPreferences(
        COVID_19_COMPANION_SHARED_PREFERENCES, Context.MODE_PRIVATE)

    suspend fun getNumberOfCheck(): Flow<Int>{
        var numberOfChecks = covid19CompanionAppSharedPref.getInt(NUMBER_OF_CHECKS, 1)
        val editor = covid19CompanionAppSharedPref.edit()
        editor.putInt(NUMBER_OF_CHECKS, ++numberOfChecks)
        editor.apply()

        return flow { emit(numberOfChecks) }
    }

}