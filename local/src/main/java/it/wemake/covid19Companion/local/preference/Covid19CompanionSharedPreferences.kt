package it.wemake.covid19Companion.local.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import it.wemake.covid19Companion.local.utils.CASES_SUMMARY_LAST_UPDATED
import it.wemake.covid19Companion.local.utils.COVID_19_COMPANION_SHARED_PREFERENCES
import it.wemake.covid19Companion.local.utils.NUMBER_OF_CHECKS
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
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

    @ExperimentalCoroutinesApi
    suspend fun getCasesSummaryLastUpdatedFlow(): Flow<String> =
        callbackFlow {
            offer(covid19CompanionAppSharedPref.getString(CASES_SUMMARY_LAST_UPDATED, "Never")!!)
            val listener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreference, key ->
                if (key == CASES_SUMMARY_LAST_UPDATED){
                    offer(sharedPreference.getString(CASES_SUMMARY_LAST_UPDATED, "Never")!!)
                }
            }
            covid19CompanionAppSharedPref.registerOnSharedPreferenceChangeListener(listener)
            awaitClose { covid19CompanionAppSharedPref.unregisterOnSharedPreferenceChangeListener(listener) }
        }

    suspend fun updateCasesSummaryLastUpdated(lastUpdated: String){
        val editor = covid19CompanionAppSharedPref.edit()
        editor.putString(CASES_SUMMARY_LAST_UPDATED, lastUpdated)
        editor.apply()
    }

    fun getCasesSummaryLastUpdated(): String =
        covid19CompanionAppSharedPref.getString(CASES_SUMMARY_LAST_UPDATED, "Never")!!

}