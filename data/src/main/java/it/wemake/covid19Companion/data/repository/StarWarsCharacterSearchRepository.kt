package it.wemake.covid19Companion.data.repository

import it.wemake.covid19Companion.data.mappers.toDomain
import it.wemake.covid19Companion.data.remote.StarWarsCharacterSearchRemoteDataSource
import it.wemake.covid19Companion.domain.models.StarWarsCharacter
import it.wemake.covid19Companion.domain.repository.ICharacterSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Co-ordinates data sources exposing search results
 */
class StarWarsCharacterSearchRepository @Inject constructor(
    private val starWarsCharacterSearchRemoteDataSource: StarWarsCharacterSearchRemoteDataSource
) : ICharacterSearchRepository {

    override suspend fun searchCharacters(params: String): Flow<List<StarWarsCharacter>> {
        return starWarsCharacterSearchRemoteDataSource.query(params).map { it.map { results->results.toDomain() } }
    }

}