package it.weMake.covid19Companion.ui.landing.help

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager

import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.FragmentHelpBinding
import it.weMake.covid19Companion.services.DownloadManagerIntentService
import it.weMake.covid19Companion.utils.*
import java.io.File

/**
 * A simple [Fragment] subclass.
 */
class HelpFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentHelpBinding

    private val WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST = 1
    private val downloadManagerBroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            binding.handHygienePB.makeDisappear()
        }
    }
    private val downloadManagerIntentFilter = IntentFilter(ACTION_DOWNLOAD_STOPPED).apply {
        addAction(ACTION_DOWNLOAD_COMPLETED)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHelpBinding.inflate(inflater, container, false)

        binding.handHygieneCV.setOnClickListener(this)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(downloadManagerBroadcastReceiver, downloadManagerIntentFilter)
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(downloadManagerBroadcastReceiver)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            downloadHandHygienePDF()
        }

    }

    override fun onClick(v: View) {
        when(v.id){

            R.id.handHygieneCV -> attemptOpenHandHygienePDF()

        }
    }

    private fun attemptOpenHandHygienePDF(){
        if (isStoragePermissionGranted()){
            if (WHOHandHygieneBrochureExists()){
                val intent = Intent(Intent.ACTION_VIEW)
                val file = File(requireContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)!!.path + File.pathSeparator + WHO_HAND_HYGIENE_PDF)
                val data = FileProvider.getUriForFile(requireContext().applicationContext, requireContext().packageName +".fileprovider", file)
                val type = "application/pdf"
                intent.setDataAndType(data, type)
                intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                startActivity(intent)
            }else{
                downloadHandHygienePDF()
            }
        }
    }

    private fun downloadHandHygienePDF(){
        binding.handHygienePB.show()
        showLongToast(requireContext(), "Downloading Hand Hygiene Brochure(477kb)")
        DownloadManagerIntentService.startActionDownloadWHOHandHygieneBrochure(requireContext())
    }

    private fun WHOHandHygieneBrochureExists(): Boolean{
        val file = File(ContextCompat.getExternalFilesDirs(requireContext(), Environment.DIRECTORY_DOCUMENTS)[0].path + "/" + WHO_HAND_HYGIENE_PDF)
        return file.exists()
    }

    private fun isStoragePermissionGranted(): Boolean{

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                return true
            }else{
                requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST)
                return false
            }
        }else{
            return true
        }

    }

}
