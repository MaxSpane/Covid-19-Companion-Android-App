package it.wemake.covid19Companion.remote.impl

import it.wemake.covid19Companion.data.models.StarWarsCharacterEntity
import it.wemake.covid19Companion.data.remote.ICharacterSearchRemote
import it.wemake.covid19Companion.remote.api.Covid19ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StarWarsCharacterSearchRemote @Inject constructor(private val apiService: Covid19ApiService): ICharacterSearchRemote {

    /**
     * Takes in [params] to be used for the search
     * @return list of search results
     */
    override suspend fun query(params: String): Flow<List<StarWarsCharacterEntity>> {
//        val searchResponse = apiService.searchCharacters(params)
        val searchDataModels = mutableListOf<StarWarsCharacterEntity>()
//        for (searchResult in searchResponse.results) {
        for (i in 1..5) {
//            searchDataModels.add(searchResult.toEntity())
            searchDataModels.add(
                StarWarsCharacterEntity(
                    "Yoda",
                    "dunno",
                    "short",
                    "lol what"
                )
            )
        }
        return flow { emit(searchDataModels) }
    }

}