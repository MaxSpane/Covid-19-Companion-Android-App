package it.weMake.covid19Companion.di.modules.repository

import it.wemake.covid19Companion.data.impl.StarWarsCharacterSearchRepository
import it.wemake.covid19Companion.domain.repository.ICharacterSearchRepository
import dagger.Binds
import dagger.Module
import it.wemake.covid19Companion.data.impl.Covid19CasesRepository
import it.wemake.covid19Companion.domain.repository.ICovid19CasesRepository
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindCharacterSearchRepository(
        starWarsCharacterSearchRepository: StarWarsCharacterSearchRepository
    ): ICharacterSearchRepository

    @Singleton
    @Binds
    abstract fun bindCovid19CasesRepository(
        covid19CasesRepository: Covid19CasesRepository
    ): ICovid19CasesRepository

}