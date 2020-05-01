package it.wemake.covid19Companion.local.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import it.wemake.covid19Companion.local.utils.CASES_SUMMARY_LAST_UPDATED
import it.wemake.covid19Companion.local.utils.COVID_19_COMPANION_SHARED_PREFERENCES
import it.wemake.covid19Companion.local.utils.NUMBER_OF_CHECKS
import it.wemake.covid19Companion.local.utils.WHO_HAND_HYGIENE_BROCHURE_DOWNLOAD_ID
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

    @ExperimentalCoroutinesApi
    suspend fun getCasesSummaryLastUpdatedFlow(): Flow<Long> =
        callbackFlow {
            offer(covid19CompanionAppSharedPref.getLong(CASES_SUMMARY_LAST_UPDATED, 0))
            val listener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreference, key ->
                if (key == CASES_SUMMARY_LAST_UPDATED){
                    offer(sharedPreference.getLong(CASES_SUMMARY_LAST_UPDATED, 0))
                }
            }
            covid19CompanionAppSharedPref.registerOnSharedPreferenceChangeListener(listener)
            awaitClose { covid19CompanionAppSharedPref.unregisterOnSharedPreferenceChangeListener(listener) }
        }

    fun updateCasesSummaryLastUpdated(lastUpdated: Long){
        val editor = covid19CompanionAppSharedPref.edit()
        editor.putLong(CASES_SUMMARY_LAST_UPDATED, lastUpdated)
        editor.apply()
    }

    fun getCasesSummaryLastUpdated(): Long =
        covid19CompanionAppSharedPref.getLong(CASES_SUMMARY_LAST_UPDATED, 0)

    fun getWHOHandHygieneDownloadId(): Long =
        covid19CompanionAppSharedPref.getLong(WHO_HAND_HYGIENE_BROCHURE_DOWNLOAD_ID, 0)

    fun setWHOHandHygieneDownloadId(downloadId: Long) {
        val editor = covid19CompanionAppSharedPref.edit()
        editor.putLong(WHO_HAND_HYGIENE_BROCHURE_DOWNLOAD_ID, downloadId)
        editor.apply()
    }

}