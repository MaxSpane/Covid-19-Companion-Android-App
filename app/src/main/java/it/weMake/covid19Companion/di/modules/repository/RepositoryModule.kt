package it.weMake.covid19Companion.di.modules.repository

import it.wemake.covid19Companion.data.impl.StarWarsCharacterSearchRepository
import it.wemake.covid19Companion.domain.repository.ICharacterSearchRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindCharacterSearchRepository(
        starWarsCharacterSearchRepository: StarWarsCharacterSearchRepository
    ): ICharacterSearchRepository

}