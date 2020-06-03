package it.weMake.covid19Companion.utils

import android.content.Context
import android.content.Intent
import android.os.Environment
import androidx.core.content.FileProvider
import it.weMake.covid19Companion.R
import java.io.File


fun openAppUpdateApk(context: Context, versionName: String){
    val intent = Intent(Intent.ACTION_VIEW)
    val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)!!.path + File.pathSeparator + context.getString(
        R.string.placeholder_app_version_file_name, versionName))
    val data = FileProvider.getUriForFile(context.applicationContext, context.packageName +".fileprovider", file)
    val type = "application/vnd.android.package-archive"
    intent.setDataAndType(data, type)
    intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_ACTIVITY_NEW_TASK
    context.startActivity(intent)
}
