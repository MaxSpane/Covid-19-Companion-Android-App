package it.weMake.covid19Companion.services

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.database.ContentObserver
import android.database.Cursor
import android.net.Uri
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import dagger.android.DaggerIntentService
import it.weMake.covid19Companion.utils.ACTION_DOWNLOAD_COMPLETED
import it.weMake.covid19Companion.utils.ACTION_DOWNLOAD_STOPPED
import it.weMake.covid19Companion.utils.DownloadManagerHelper
import it.weMake.covid19Companion.utils.WHO_HAND_HYGIENE_PDF_URL
import it.wemake.covid19Companion.domain.usecases.GetWHOHandHygieneBrochureDownloadIdLastUpdatedUseCase
import it.wemake.covid19Companion.domain.usecases.SetWHOHandHygieneBrochureDownloadIdLastUpdatedUseCase
import javax.inject.Inject

private const val ACTION_DOWNLOAD_FILE = "it.weMake.covid19Companion.action.DOWNLOAD_FILE"
private const val ACTION_DOWNLOAD_WHO_HAND_HYGIENE_BROCHURE = "it.weMake.covid19Companion.action.WHO_HAND_HYGIENE_BROCHURE"

private const val EXTRA_DOWNLOAD_URL = "it.weMake.covid19Companion.extra.DOWNLOAD_URL"
private const val EXTRA_SUB_DIR_DESTINATION = "it.weMake.covid19Companion.extra.SUB_DIR_DESTINATION"

class DownloadManagerIntentService : DownloadManagerHelper("DownloadManagerIntentService") {

    @Inject
    lateinit var getWHOHandHygieneBrochureDownloadIdLastUpdatedUseCase: GetWHOHandHygieneBrochureDownloadIdLastUpdatedUseCase

    @Inject
    lateinit var setWHOHandHygieneBrochureDownloadIdLastUpdatedUseCase: SetWHOHandHygieneBrochureDownloadIdLastUpdatedUseCase

    override fun onHandleIntent(intent: Intent?) {
        when (intent?.action) {
            ACTION_DOWNLOAD_FILE -> {
                val downloadURL = intent.getStringExtra(EXTRA_DOWNLOAD_URL)!!
                val subDirDestination = intent.getStringExtra(EXTRA_SUB_DIR_DESTINATION)!!
                handleActionDownloadFile(downloadURL, subDirDestination)
            }
            ACTION_DOWNLOAD_WHO_HAND_HYGIENE_BROCHURE -> {
                handleActionDownloadWHOHandHygieneBrochure()
            }
        }
    }

    private fun handleActionDownloadFile(downloadURL: String, subDirDestination: String) {
        startDownload(downloadURL, subDirDestination)
    }

    private fun handleActionDownloadWHOHandHygieneBrochure() {
        val downloadId = startDownload(WHO_HAND_HYGIENE_PDF_URL, "")
        setWHOHandHygieneBrochureDownloadIdLastUpdatedUseCase.invoke(downloadId)
    }

    companion object {
        @JvmStatic
        fun startActionDownloadFile(context: Context, downloadURL: String, subDirDestination: String) {
            val intent = Intent(context, DownloadManagerIntentService::class.java).apply {
                action = ACTION_DOWNLOAD_FILE
                putExtra(EXTRA_DOWNLOAD_URL, downloadURL)
                putExtra(EXTRA_SUB_DIR_DESTINATION, subDirDestination)
            }
            context.startService(intent)
        }

        @JvmStatic
        fun startActionDownloadWHOHandHygieneBrochure(context: Context) {
            val intent = Intent(context, DownloadManagerIntentService::class.java).apply {
                action = ACTION_DOWNLOAD_WHO_HAND_HYGIENE_BROCHURE
            }
            context.startService(intent)
        }

    }

    override fun downloadPaused(downloadId: Long) {
        super.downloadPaused(downloadId)
        resetWHOHandHygieneBrochureDownloadId(downloadId)
    }

    override fun downloadRunning(downloadId: Long) {
        super.downloadRunning(downloadId)
    }

    override fun downloadSuccessful(downloadId: Long) {
        super.downloadSuccessful(downloadId)
        resetWHOHandHygieneBrochureDownloadId(downloadId)
    }

    override fun downloadFailed(downloadId: Long) {
        super.downloadFailed(downloadId)
        resetWHOHandHygieneBrochureDownloadId(downloadId)
    }

    override fun downloadCanceled(downloadId: Long) {
        super.downloadCanceled(downloadId)
        resetWHOHandHygieneBrochureDownloadId(downloadId)
    }

    private fun resetWHOHandHygieneBrochureDownloadId(downloadId: Long){
        if (downloadId == getWHOHandHygieneBrochureDownloadIdLastUpdatedUseCase.invoke()){
            setWHOHandHygieneBrochureDownloadIdLastUpdatedUseCase.invoke(0)
        }
    }

}
