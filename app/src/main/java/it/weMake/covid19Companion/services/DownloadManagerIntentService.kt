package it.weMake.covid19Companion.services

import android.content.Intent
import android.content.Context
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.utils.*
import it.wemake.covid19Companion.domain.usecases.GetAppUpdateDownloadIdUseCase
import it.wemake.covid19Companion.domain.usecases.GetWHOHandHygieneBrochureDownloadIdUseCase
import it.wemake.covid19Companion.domain.usecases.SetAppUpdateDownloadIdUseCase
import it.wemake.covid19Companion.domain.usecases.SetWHOHandHygieneBrochureDownloadIdUseCase
import javax.inject.Inject

private const val ACTION_DOWNLOAD_FILE = "it.weMake.covid19Companion.action.DOWNLOAD_FILE"
private const val ACTION_DOWNLOAD_WHO_HAND_HYGIENE_BROCHURE = "it.weMake.covid19Companion.action.WHO_HAND_HYGIENE_BROCHURE"
private const val ACTION_DOWNLOAD_APP_UPDATE = "it.weMake.covid19Companion.action.APP_UPDATE"

private const val EXTRA_DOWNLOAD_URL = "it.weMake.covid19Companion.extra.DOWNLOAD_URL"
private const val EXTRA_SUB_DIR_DESTINATION = "it.weMake.covid19Companion.extra.SUB_DIR_DESTINATION"
private const val EXTRA_LATEST_APP_VERSION_NAME = "it.weMake.covid19Companion.extra.LATEST_APP_VERSION_NAME"

class DownloadManagerIntentService : DownloadManagerHelper("DownloadManagerIntentService") {

    @Inject
    lateinit var getWHOHandHygieneBrochureDownloadIdUseCase: GetWHOHandHygieneBrochureDownloadIdUseCase

    @Inject
    lateinit var setWHOHandHygieneBrochureDownloadIdUseCase: SetWHOHandHygieneBrochureDownloadIdUseCase

    @Inject
    lateinit var getAppUpdateDownloadIdUseCase: GetAppUpdateDownloadIdUseCase

    @Inject
    lateinit var setAppUpdateDownloadIdUseCase: SetAppUpdateDownloadIdUseCase

    private var appUpdateVersionNumber: String? = null

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

            ACTION_DOWNLOAD_APP_UPDATE -> {
                val versionName = intent.getStringExtra(EXTRA_LATEST_APP_VERSION_NAME)!!
                handleActionDownloadAppUpdate(versionName)
            }
        }
    }

    private fun handleActionDownloadFile(downloadURL: String, subDirDestination: String) {
        startDownload(downloadURL, subDirDestination)
    }

    private fun handleActionDownloadWHOHandHygieneBrochure() {
        val downloadId = startDownload(WHO_HAND_HYGIENE_PDF_URL, WHO_HAND_HYGIENE_PDF)
        setWHOHandHygieneBrochureDownloadIdUseCase.invoke(downloadId)
    }

    private fun handleActionDownloadAppUpdate(versionName: String) {
        appUpdateVersionNumber = versionName
        setAppUpdateDownloadIdUseCase(startDownload(
            APP_DOWNLOAD_URL,
            getString(R.string.placeholder_app_version_file_name, versionName)
        ))
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

        @JvmStatic
        fun startActionDownloadAppUpdate(context: Context, latestVersionName: String) {
            val intent = Intent(context, DownloadManagerIntentService::class.java).apply {
                action = ACTION_DOWNLOAD_APP_UPDATE
            }
            intent.putExtra(EXTRA_LATEST_APP_VERSION_NAME, latestVersionName)
            context.startService(intent)
        }

    }

    override fun downloadPaused(downloadId: Long) {
        super.downloadPaused(downloadId)
    }

    override fun downloadRunning(downloadId: Long) {
        super.downloadRunning(downloadId)
    }

    override fun downloadSuccessful(downloadId: Long) {
        if (downloadId == getAppUpdateDownloadIdUseCase()){
            openAppUpdateApk(this, appUpdateVersionNumber!!)
        }else if(downloadId == getWHOHandHygieneBrochureDownloadIdUseCase()){
            openHandsHygieneBrochure(this)
        }
        super.downloadSuccessful(downloadId)
    }

    override fun downloadFailed(downloadId: Long) {
        resetDownloadId(downloadId)
        super.downloadFailed(downloadId)
    }

    override fun downloadCanceled(downloadId: Long) {
        resetDownloadId(downloadId)
        super.downloadCanceled(downloadId)
    }

    private fun resetDownloadId(downloadId: Long){
        if (downloadId == getWHOHandHygieneBrochureDownloadIdUseCase()){
            setWHOHandHygieneBrochureDownloadIdUseCase(0)
        }else if(downloadId == getAppUpdateDownloadIdUseCase()){
            setAppUpdateDownloadIdUseCase(0)
        }
    }

}
