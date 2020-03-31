package it.weMake.covid19Companion.di.modules.domain

import dagger.Binds
import dagger.Module
import dagger.Provides
import it.wemake.covid19Companion.data.impl.Covid19CasesRepository
import it.wemake.covid19Companion.data.impl.SharedPreferencesRepository
import it.wemake.covid19Companion.data.impl.StarWarsCharacterSearchRepository
import it.wemake.covid19Companion.data.remote.ICharacterSearchRemote
import it.wemake.covid19Companion.domain.usecases.GetCountriesUseCase
import it.wemake.covid19Companion.domain.usecases.GetNumberOfTriesUseCase
import it.wemake.covid19Companion.domain.usecases.InsertCountriesUseCase
import it.wemake.covid19Companion.domain.usecases.SearchStarWarsCharacterUseCase
import it.wemake.covid19Companion.remote.impl.StarWarsCharacterSearchRemote
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    fun provideCharacterSearchUseCase(
        starWarsCharacterSearchRepository: StarWarsCharacterSearchRepository
    ): SearchStarWarsCharacterUseCase = SearchStarWarsCharacterUseCase(starWarsCharacterSearchRepository)

    @Provides
    fun provideGetCountriesUseCase(
        covid19CasesRepository: Covid19CasesRepository
    ): GetCountriesUseCase = GetCountriesUseCase(covid19CasesRepository)

    @Provides
    fun provideInsertCountriesUseCase(
        covid19CasesRepository: Covid19CasesRepository
    ): InsertCountriesUseCase = InsertCountriesUseCase(covid19CasesRepository)

    @Provides
    fun provideSharedPreferencesUseCase(
        sharedPreferencesRepository: SharedPreferencesRepository
    ): GetNumberOfTriesUseCase = GetNumberOfTriesUseCase(sharedPreferencesRepository)

}