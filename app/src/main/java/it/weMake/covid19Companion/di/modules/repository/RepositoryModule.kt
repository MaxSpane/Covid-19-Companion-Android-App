package it.weMake.covid19Companion.di.modules.repository

import dagger.Binds
import dagger.Module
import it.wemake.covid19Companion.data.impl.Covid19CasesRepository
import it.wemake.covid19Companion.data.impl.SharedPreferencesRepository
import it.wemake.covid19Companion.domain.repository.ICovid19CasesRepository
import it.wemake.covid19Companion.domain.repository.ISharedPreferencesRepository
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindCovid19CasesRepository(
        covid19CasesRepository: Covid19CasesRepository
    ): ICovid19CasesRepository

    @Singleton
    @Binds
    abstract fun bindSharedPreferencesRepository(
        sharedPreferencesRepository: SharedPreferencesRepository
    ): ISharedPreferencesRepository

}