package it.weMake.covid19Companion.commons

import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class GenericPagedDataSource<Data> (
    val loadData: suspend (page: Int) -> Flow<List<Data>>
): PageKeyedDataSource<Int, Data>(), CoroutineScope by CoroutineScope(Dispatchers.IO) {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Data>
    ) {
        launch{
            loadData(0).collect {
                callback.onResult(it, null, 1)
                println("loadInitial")
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Data>) {
        launch{
            loadData(params.key + 1).collect {
                callback.onResult(it, params.key + 2)
                println("loadAfter")
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Data>) {
        launch{
            loadData(params.key - 1).collect {
                callback.onResult(it, params.key - 2)
                println("loadBefore")
            }
        }
    }

    fun destroy(){
        cancel()
    }
}