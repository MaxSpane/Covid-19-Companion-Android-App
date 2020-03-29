package it.weMake.covid19Companion.di.modules.search

import it.wemake.covid19Companion.data.repository.StarWarsCharacterSearchRepository
import it.wemake.covid19Companion.domain.repository.ICharacterSearchRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class CharacterSearchBindingModule {

    @Singleton
    @Binds
    abstract fun bindCharacterSearchRepository(
        starWarsCharacterSearchRepository: StarWarsCharacterSearchRepository
    ): ICharacterSearchRepository

}