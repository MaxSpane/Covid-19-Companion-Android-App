package it.weMake.covid19Companion.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.weMake.covid19Companion.mappers.toPresentation
import it.weMake.covid19Companion.models.appReleases.AppRelease
import it.weMake.covid19Companion.models.developmentTeam.TeamMember
import it.weMake.covid19Companion.models.sources.Source
import it.wemake.covid19Companion.domain.usecases.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class AboutViewModel @Inject constructor(
    private val getLatestVersionCodeUseCase: GetLatestVersionCodeUseCase,
    private val getAppReleasesUseCase: GetAppReleasesUseCase,
    private val getAppUpdateDownloadIdUseCase: GetAppUpdateDownloadIdUseCase,
    private val getAllSourcesUseCase: GetAllSourcesUseCase,
    private val getDevelopmentTeamMembersUseCase: GetDevelopmentTeamMembersUseCase
): ViewModel() {

    lateinit var latestVersionName: String
    private val _appReleasesLiveData = MutableLiveData<List<AppRelease>>()
    val appReleasesLiveData: LiveData<List<AppRelease>>
        get() = _appReleasesLiveData
    private val _sourcesLiveData = MutableLiveData<List<Source>>()
    val sourcesLiveData: LiveData<List<Source>>
        get() = _sourcesLiveData
    private val _teamMembersLiveData = MutableLiveData<List<TeamMember>>()
    val teamMembersLiveData: LiveData<List<TeamMember>>
        get() = _teamMembersLiveData

    init {
        viewModelScope.launch {
            getAppReleasesUseCase().map { it.map { it.toPresentation() } }.collect {
                latestVersionName =
                    if(it.isEmpty()){
                        "1.0"
                    }else{
                        it[0].versionName
                    }
                _appReleasesLiveData.value = it
            }
        }

        viewModelScope.launch {
            getAllSourcesUseCase().map { it.map { it.toPresentation() } }.collect {
                _sourcesLiveData.value = it
            }
        }

        viewModelScope.launch {
            getDevelopmentTeamMembersUseCase().map { it.map { it.toPresentation() } }.collect {
                _teamMembersLiveData.value = it
            }
        }
    }

    fun getLatestVersionCode(): Int =
        getLatestVersionCodeUseCase()

    fun getAppUpdateDownloadId(): Long =
        getAppUpdateDownloadIdUseCase()

}