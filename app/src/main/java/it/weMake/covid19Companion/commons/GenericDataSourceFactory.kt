package it.weMake.covid19Companion.commons

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import kotlinx.coroutines.flow.Flow

class GenericDataSourceFactory<Data> (
    val loadData: suspend (page: Int) -> Flow<List<Data>>
): DataSource.Factory<Int, Data>(){

    val sourceLiveData = MutableLiveData<GenericPagedDataSource<Data>>()
    lateinit var latestSource: GenericPagedDataSource<Data>

    override fun create(): DataSource<Int, Data> {
        latestSource = GenericPagedDataSource(loadData)
        sourceLiveData.postValue(latestSource)
        return latestSource
    }

}