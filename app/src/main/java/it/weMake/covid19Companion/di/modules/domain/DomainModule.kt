package it.weMake.covid19Companion.di.modules.domain

import dagger.Module
import dagger.Provides
import it.wemake.covid19Companion.data.impl.*
import it.wemake.covid19Companion.domain.usecases.*

@Module
class DomainModule {

    @Provides
    fun provideUpdateCasesDataUseCase(
        covid19CasesRepository: Covid19CasesRepository
    ): UpdateCasesDataUseCase = UpdateCasesDataUseCase(covid19CasesRepository)

    @Provides
    fun provideGetGlobalCasesDataUseCase(
        covid19CasesRepository: Covid19CasesRepository
    ): GetGlobalCasesDataUseCase = GetGlobalCasesDataUseCase(covid19CasesRepository)

    @Provides
    fun provideGetWHOHandHygieneBrochureDownloadIdLastUpdatedUseCase(
        sharedPreferencesRepository: SharedPreferencesRepository
    ): GetWHOHandHygieneBrochureDownloadIdLastUpdatedUseCase = GetWHOHandHygieneBrochureDownloadIdLastUpdatedUseCase(sharedPreferencesRepository)

    @Provides
    fun provideSetWHOHandHygieneBrochureDownloadIdLastUpdatedUseCase(
        sharedPreferencesRepository: SharedPreferencesRepository
    ): SetWHOHandHygieneBrochureDownloadIdLastUpdatedUseCase = SetWHOHandHygieneBrochureDownloadIdLastUpdatedUseCase(sharedPreferencesRepository)

    @Provides
    fun provideGetCountriesCasesDataCasesUseCase(
        covid19CasesRepository: Covid19CasesRepository
    ): GetCountriesCasesDataUseCase = GetCountriesCasesDataUseCase(covid19CasesRepository)

    @Provides
    fun provideSearchCountriesCasesDataCasesUseCase(
        covid19CasesRepository: Covid19CasesRepository
    ): SearchCountriesCasesDataUseCase = SearchCountriesCasesDataUseCase(covid19CasesRepository)

    @Provides
    fun provideGetPreventionTipsUseCase(
        preventionTipsRepository: PreventionTipsRepository
    ): GetPreventionTipsUseCase = GetPreventionTipsUseCase(preventionTipsRepository)

    @Provides
    fun provideRequestNextQuestionUseCase(
        screeningToolRepository: ScreeningToolRepository
    ): RequestNextQuestionUseCase = RequestNextQuestionUseCase(screeningToolRepository)

    @Provides
    fun provideGetDiagnosisUseCase(
        screeningToolRepository: ScreeningToolRepository
    ): GetDiagnosisUseCase = GetDiagnosisUseCase(screeningToolRepository)

    @Provides
    fun provideGetCountriesUseCase(
        countriesRepository: CountriesRepository
    ): GetCountriesUseCase = GetCountriesUseCase(countriesRepository)

    @Provides
    fun provideGetUserCountryIso2UseCase(
        sharedPreferencesRepository: SharedPreferencesRepository
    ): GetUserCountryIso2UseCase = GetUserCountryIso2UseCase(sharedPreferencesRepository)

    @Provides
    fun provideSetUserCountryIso2UseCase(
        sharedPreferencesRepository: SharedPreferencesRepository
    ): SetUserCountryIso2UseCase = SetUserCountryIso2UseCase(sharedPreferencesRepository)

    @Provides
    fun provideGetUserCountryCasesDataUseCase(
        covid19CasesRepository: Covid19CasesRepository
    ): GetUserCountryCasesDataUseCase = GetUserCountryCasesDataUseCase(covid19CasesRepository)

    @Provides
    fun provideGetWashHandsIntervalUseCase(
        sharedPreferencesRepository: SharedPreferencesRepository
    ): GetWashHandsIntervalUseCase {
            return GetWashHandsIntervalUseCase(sharedPreferencesRepository)
    }

    @Provides
    fun provideSetWashHandsIntervalUseCase(
        sharedPreferencesRepository: SharedPreferencesRepository
    ): SetWashHandsIntervalUseCase {
            return SetWashHandsIntervalUseCase(sharedPreferencesRepository)
    }

    @Provides
    fun provideGetDrinkWaterIntervalUseCase(
        sharedPreferencesRepository: SharedPreferencesRepository
    ): GetDrinkWaterIntervalUseCase {
            return  GetDrinkWaterIntervalUseCase(sharedPreferencesRepository)
    }

    @Provides
    fun provideSetDrinkWaterIntervalUseCase(
        sharedPreferencesRepository: SharedPreferencesRepository
    ): SetDrinkWaterIntervalUseCase {
            return SetDrinkWaterIntervalUseCase(sharedPreferencesRepository)
    }

    @Provides
    fun provideGetUseCustomNotificationToneUseCase(
        sharedPreferencesRepository: SharedPreferencesRepository
    ): GetUseCustomNotificationToneUseCase {
            return GetUseCustomNotificationToneUseCase(sharedPreferencesRepository)
    }

    @Provides
    fun provideSetUseCustomNotificationToneUseCase(
        sharedPreferencesRepository: SharedPreferencesRepository
    ): SetUseCustomNotificationToneUseCase {
            return SetUseCustomNotificationToneUseCase(sharedPreferencesRepository)
    }

}