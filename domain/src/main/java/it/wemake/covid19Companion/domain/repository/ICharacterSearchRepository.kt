package it.wemake.covid19Companion.domain.repository

import it.wemake.covid19Companion.domain.models.StarWarsCharacter
import kotlinx.coroutines.flow.Flow

interface ICharacterSearchRepository {
    suspend fun searchCharacters(params: String): Flow<List<StarWarsCharacter>>
}