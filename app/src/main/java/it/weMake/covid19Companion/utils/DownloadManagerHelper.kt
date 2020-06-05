package it.weMake.covid19Companion.utils

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.database.ContentObserver
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import dagger.android.DaggerIntentService
import java.net.URLEncoder

val EXTRA_DOWNLOAD_ID = "it.weMake.covid19Companion.extra.DOWNLOAD_ID"

abstract class DownloadManagerHelper(val name: String): DaggerIntentService(name){

    private var contentObserver: ContentObserver? = null

    fun startDownload(downloadUrl: String, fileName: String): Long{
        val downloadManager = this.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val downloadRequest = DownloadManager.Request(Uri.parse(downloadUrl))

        downloadRequest.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
            .setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOCUMENTS, fileName)

        val downloadId = downloadManager.enqueue(downloadRequest)
        registerContentObserver(downloadId)

        return downloadId
    }

    fun registerContentObserver(downloadId: Long){
        contentObserver = object : ContentObserver(null) {
            override fun onChange(selfChange: Boolean, uri: Uri) {
                super.onChange(selfChange, uri)
                if (uri.toString().matches(Regex(".*\\d+$"))) {
                    val changedId = uri.lastPathSegment!!.toLong()
                    if (changedId == downloadId) {
                        Log.d(
                            "DownloadManagerHelper",
                            "onChange: $uri $changedId $downloadId"
                        )
                        var cursor: Cursor? = null
                        try {
                            cursor = this@DownloadManagerHelper.contentResolver.query(uri, null, null, null, null)
                            if (cursor != null && cursor.moveToFirst()) {
                                Log.d("DownloadManagerHelper", "onChange: running")
                                handleDownloadStatus(downloadId, cursor.getInt(cursor.getColumnIndex("status")))
                            } else {
                                Log.w("DownloadManagerHelper", "onChange: cancel")
                                downloadCanceled(downloadId)
                            }
                        } finally {
                            cursor?.let {
                                it.close()
                            }
                        }
                    }
                }
            }
        }
        this.contentResolver.registerContentObserver(
            Uri.parse("content://downloads/my_downloads"),
            true, contentObserver!!)
    }

    fun unregisterContentObserver() =
        contentObserver?.let {
            this.contentResolver.unregisterContentObserver(it)
        }

    open fun handleDownloadStatus(downloadId: Long, downloadStatus: Int){

        when(downloadStatus){

            DownloadManager.STATUS_PAUSED -> downloadPaused(downloadId)

            DownloadManager.STATUS_RUNNING -> downloadRunning(downloadId)

            200 -> {
                unregisterContentObserver()
                downloadSuccessful(downloadId)
            }

            DownloadManager.STATUS_FAILED -> downloadFailed(downloadId)

        }

    }

    open fun downloadPaused(downloadId: Long) {
        LocalBroadcastManager.getInstance(this).sendBroadcast(
            Intent().also{
                it.action = ACTION_DOWNLOAD_STOPPED
                it.putExtra(EXTRA_DOWNLOAD_ID, downloadId)
            }
        )
    }

    open fun downloadRunning(downloadId: Long) {
        LocalBroadcastManager.getInstance(this).sendBroadcast(
            Intent().also{
                it.action = ACTION_DOWNLOAD_RUNNING
                it.putExtra(EXTRA_DOWNLOAD_ID, downloadId)
            }
        )
    }

    open fun downloadSuccessful(downloadId: Long) {
        LocalBroadcastManager.getInstance(this).sendBroadcast(
            Intent().also{
                it.action = ACTION_DOWNLOAD_COMPLETED
                it.putExtra(EXTRA_DOWNLOAD_ID, downloadId)
            }
        )
        stopSelf()
    }

    open fun downloadFailed(downloadId: Long) {
        LocalBroadcastManager.getInstance(this).sendBroadcast(
            Intent().also{
                it.action = ACTION_DOWNLOAD_STOPPED
                it.putExtra(EXTRA_DOWNLOAD_ID, downloadId)
            }
        )
        stopSelf()
    }

    open fun downloadCanceled(downloadId: Long) {
        LocalBroadcastManager.getInstance(this).sendBroadcast(
            Intent().also{
                it.action = ACTION_DOWNLOAD_STOPPED
                it.putExtra(EXTRA_DOWNLOAD_ID, downloadId)
            }
        )
        stopSelf()
    }

}