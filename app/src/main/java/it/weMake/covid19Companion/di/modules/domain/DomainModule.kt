package it.weMake.covid19Companion.di.modules.domain

import dagger.Module
import dagger.Provides
import it.wemake.covid19Companion.data.impl.Covid19CasesRepository
import it.wemake.covid19Companion.data.impl.SharedPreferencesRepository
import it.wemake.covid19Companion.domain.usecases.*

@Module
class DomainModule {

    @Provides
    fun provideGetCountriesCasesUseCase(
        covid19CasesRepository: Covid19CasesRepository
    ): GetCountriesCasesUseCase = GetCountriesCasesUseCase(covid19CasesRepository)

    @Provides
    fun provideUpdateCountriesCasesUseCase(
        covid19CasesRepository: Covid19CasesRepository
    ): UpdateCountriesCasesUseCase = UpdateCountriesCasesUseCase(covid19CasesRepository)

    @Provides
    fun provideCasesStatsUseCase(
        covid19CasesRepository: Covid19CasesRepository
    ): CasesStatsUseCase = CasesStatsUseCase(covid19CasesRepository)

    @Provides
    fun provideGetWHOHandHygieneBrochureDownloadIdLastUpdatedUseCase(
        sharedPreferencesRepository: SharedPreferencesRepository
    ): GetWHOHandHygieneBrochureDownloadIdLastUpdatedUseCase = GetWHOHandHygieneBrochureDownloadIdLastUpdatedUseCase(sharedPreferencesRepository)

    @Provides
    fun provideSetWHOHandHygieneBrochureDownloadIdLastUpdatedUseCase(
        sharedPreferencesRepository: SharedPreferencesRepository
    ): SetWHOHandHygieneBrochureDownloadIdLastUpdatedUseCase = SetWHOHandHygieneBrochureDownloadIdLastUpdatedUseCase(sharedPreferencesRepository)

}