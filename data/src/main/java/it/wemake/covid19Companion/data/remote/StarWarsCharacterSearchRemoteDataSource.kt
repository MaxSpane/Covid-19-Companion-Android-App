package it.wemake.covid19Companion.data.remote

import it.wemake.covid19Companion.data.models.entity.StarWarsCharacterEntity
import it.wemake.covid19Companion.data.api.Covid19ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StarWarsCharacterSearchRemoteDataSource @Inject constructor(private val apiService: Covid19ApiService) {

    /**
     * Takes in [params] to be used for the search
     * @return list of search results
     */
    suspend fun query(params: String): Flow<List<StarWarsCharacterEntity>> {
//        val searchResponse = apiService.searchCharacters(params)
        val searchDataModels = mutableListOf<StarWarsCharacterEntity>()
//        for (searchResult in searchResponse.results) {
        for (i in 1..5) {
//            searchDataModels.add(searchResult.toEntity())
            searchDataModels.add(StarWarsCharacterEntity("Yoda", "dunno", "short", "lol what"))
        }
        return flow { emit(searchDataModels) }
    }

}