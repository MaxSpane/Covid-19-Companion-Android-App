package it.weMake.covid19Companion.ui.landing.dashboard

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.k0d4black.theforce.commons.Error
import com.k0d4black.theforce.commons.Loading
import com.k0d4black.theforce.commons.Success
import dagger.android.support.DaggerFragment
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.databinding.FragmentDashboardBinding
import it.weMake.covid19Companion.services.DownloadManagerIntentService
import it.weMake.covid19Companion.ui.landing.dashboard.adapters.DashboardAdapter
import it.weMake.covid19Companion.utils.*
import java.io.File
import javax.inject.Inject

class DashboardFragment : DaggerFragment(), View.OnClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected val viewModel: DashboardViewModel by viewModels { viewModelFactory }

    lateinit var binding: FragmentDashboardBinding
    lateinit var dashboardAdapter: DashboardAdapter

    private val downloadManagerBroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            dashboardAdapter.setIsDownloadingHandHygieneBrochure(false)
        }

    }
    private val downloadManagerIntentFilter = IntentFilter(ACTION_DOWNLOAD_STOPPED).apply {
        addAction(ACTION_DOWNLOAD_COMPLETED)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)

        dashboardAdapter = DashboardAdapter(attemptDownloadHandHygienePDF, search)

        binding.dashboardRV.adapter = dashboardAdapter

        attachObservers()

        viewModel.updateCasesData()

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

    override fun onClick(v: View) {
    }

    private val WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST = 1
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

    private fun attachObservers(){

        viewModel.casesDataLastUpdated.observe(viewLifecycleOwner, Observer {
            if(it == 0L){
                dashboardAdapter.setLastUpdated("Never")
            }else {
                dashboardAdapter.setLastUpdated(it.getTimeFromToday())
            }
        })

        viewModel.globalCasesData.observe(viewLifecycleOwner, Observer {
            it?.let {
                dashboardAdapter.setGlobalCasesData(it)
            }
        })

        viewModel.pagedCountriesCasesData.observe(viewLifecycleOwner, Observer {
            dashboardAdapter.addPage(it)
        })

//        dashboardViewModel.updateCasesSummary()

        binding.dashboardRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dashboardAdapter.itemCount != 3){
                    if(dy > 0){
                        Log.i("RecyclerView scrolled: ", "scroll up!")
                        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                        if (layoutManager.findLastVisibleItemPosition() == 17)
                            viewModel.loadPage(dashboardAdapter.pageBottom + 1)
                    }
                    else{
                        Log.i("RecyclerView scrolled: ", "scroll down!")
                        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                        if (layoutManager.findFirstVisibleItemPosition() == 3 && dashboardAdapter.pageTop != 0)
                            viewModel.loadPage(dashboardAdapter.pageTop - 1)
                    }
                }

            }

        })

        viewModel.loadPage(0, 20)

        viewModel.uiState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Success -> binding.dashboardSRL.isRefreshing = false
                is Error -> {
                    binding.dashboardSRL.isRefreshing = false
                    showLongToast(requireContext(), "Could not update Cases Data. Please try again.")
                }
                is Loading -> binding.dashboardSRL.isRefreshing = true
            }
        })

        binding.dashboardSRL.setOnRefreshListener { viewModel.updateCasesData() }


        viewModel.pagedSearchCountriesCasesData.observe(viewLifecycleOwner, Observer {
            dashboardAdapter.refill(it)
        })

    }

    private val attemptDownloadHandHygienePDF = fun (){
        if (isStoragePermissionGranted()){
            if (WHOHandHygieneBrochureExists()){
                val intent = Intent(Intent.ACTION_VIEW)
                val file = File(requireContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)!!.path + File.pathSeparator + WHO_HAND_HYGIENE_PDF)
                val data = FileProvider.getUriForFile(requireContext().applicationContext, requireContext().packageName +".fileprovider", file)
                val type = "application/pdf"
                intent.setDataAndType(data, type)
                intent.flags = FLAG_GRANT_READ_URI_PERMISSION or FLAG_GRANT_WRITE_URI_PERMISSION
                startActivity(intent)
            }else{
                downloadHandHygienePDF()
            }
        }
    }

    private fun downloadHandHygienePDF(){
        dashboardAdapter.setIsDownloadingHandHygieneBrochure(true)
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

    private val search = fun (searchQuery: String){
        if (searchQuery.isEmpty()){
            dashboardAdapter.refill(viewModel.pagedCountriesCasesData.value!!)
        }else{
            viewModel.pagedSearch("%$searchQuery%", 0, 20)
        }
    }

}
