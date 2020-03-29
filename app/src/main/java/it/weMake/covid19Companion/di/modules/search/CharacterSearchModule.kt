package it.weMake.covid19Companion.di.modules.search

import it.wemake.covid19Companion.data.repository.StarWarsCharacterSearchRepository
import it.wemake.covid19Companion.data.remote.StarWarsCharacterSearchRemoteDataSource
import it.wemake.covid19Companion.domain.usecases.SearchStarWarsCharacterUseCase
import dagger.Module
import dagger.Provides
import it.wemake.covid19Companion.data.api.Covid19ApiService

@Module
class CharacterSearchModule {

//    @Provides
//    fun provideSearchQueryListener(characterSearchViewModel: CharacterSearchViewModel): SearchQueryListener {
//        return SearchQueryListener(characterSearchViewModel)
//    }

    @Provides
    fun provideCharacterSearchUseCase(
        starWarsCharacterSearchRepository: StarWarsCharacterSearchRepository
    ): SearchStarWarsCharacterUseCase = SearchStarWarsCharacterUseCase(starWarsCharacterSearchRepository)

    @Provides
    fun provideCharacterSearchDataSource(apiService: Covid19ApiService): StarWarsCharacterSearchRemoteDataSource =
        StarWarsCharacterSearchRemoteDataSource(apiService)

}


