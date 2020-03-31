package it.wemake.covid19Companion.data.remote

import it.wemake.covid19Companion.data.models.StarWarsCharacterEntity
import kotlinx.coroutines.flow.Flow

interface ICharacterSearchRemote {

    suspend fun query(params: String): Flow<List<StarWarsCharacterEntity>>

}