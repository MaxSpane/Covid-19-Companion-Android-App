package it.weMake.covid19Companion.di.modules.domain

import dagger.Binds
import dagger.Module
import dagger.Provides
import it.wemake.covid19Companion.data.impl.StarWarsCharacterSearchRepository
import it.wemake.covid19Companion.data.remote.ICharacterSearchRemote
import it.wemake.covid19Companion.domain.usecases.SearchStarWarsCharacterUseCase
import it.wemake.covid19Companion.remote.impl.StarWarsCharacterSearchRemote
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    fun provideCharacterSearchUseCase(
        starWarsCharacterSearchRepository: StarWarsCharacterSearchRepository
    ): SearchStarWarsCharacterUseCase = SearchStarWarsCharacterUseCase(starWarsCharacterSearchRepository)

}