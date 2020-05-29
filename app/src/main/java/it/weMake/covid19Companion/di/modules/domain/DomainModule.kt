package it.weMake.covid19Companion.di.modules.domain

import dagger.Module
import dagger.Provides
import it.wemake.covid19Companion.data.impl.*
import it.wemake.covid19Companion.domain.repository.IWashHandsReminderLocationsRepository
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

    @Provides
    fun provideGetAllWashHandsReminderLocationsUseCase(
        washHandsReminderLocationsRepository: IWashHandsReminderLocationsRepository
    ): GetAllWashHandsReminderLocationsUseCase {
            return GetAllWashHandsReminderLocationsUseCase(washHandsReminderLocationsRepository)
    }

    @Provides
    fun provideInsertWashHandsReminderLocationUseCase(
        washHandsReminderLocationsRepository: IWashHandsReminderLocationsRepository
    ): InsertWashHandsReminderLocationUseCase {
            return InsertWashHandsReminderLocationUseCase(washHandsReminderLocationsRepository)
    }

    @Provides
    fun provideDeleteWashHandsReminderLocationUseCase(
        washHandsReminderLocationsRepository: IWashHandsReminderLocationsRepository
    ): DeleteWashHandsReminderLocationUseCase {
            return DeleteWashHandsReminderLocationUseCase(washHandsReminderLocationsRepository)
    }

    @Provides
    fun provideUpdateWashHandsReminderLocationUseCase(
        washHandsReminderLocationsRepository: IWashHandsReminderLocationsRepository
    ): UpdateWashHandsReminderLocationUseCase {
            return UpdateWashHandsReminderLocationUseCase(washHandsReminderLocationsRepository)
    }

    @Provides
    fun provideGetRemindUserToWashHandsWhenArrivedAtLocationUseCase(
        sharedPreferencesRepository: SharedPreferencesRepository
    ): GetRemindUserToWashHandsWhenArrivedAtLocationUseCase {
        return GetRemindUserToWashHandsWhenArrivedAtLocationUseCase(sharedPreferencesRepository)
    }

    @Provides
    fun provideSetRemindUserToWashHandsWhenArrivedAtLocationUseCase(
        sharedPreferencesRepository: SharedPreferencesRepository
    ): SetRemindUserToWashHandsWhenArrivedAtLocationUseCase {
        return SetRemindUserToWashHandsWhenArrivedAtLocationUseCase(sharedPreferencesRepository)
    }

    @Provides
    fun provideGetWashHandsReminderLocationUseCase(
        washHandsReminderLocationsRepository: IWashHandsReminderLocationsRepository
    ): GetWashHandsReminderLocationUseCase {
        return GetWashHandsReminderLocationUseCase(washHandsReminderLocationsRepository)
    }

    @Provides
    fun provideGetAllCountryRegionsCasesDataUseCase(
        covid19CasesRepository: Covid19CasesRepository
    ): GetAllCountryRegionsCasesDataUseCase {
            return GetAllCountryRegionsCasesDataUseCase(covid19CasesRepository)
    }

    @Provides
    fun provideUpdateCountryRegionsCasesDataUseCase(
        covid19CasesRepository: Covid19CasesRepository
    ): UpdateCountryRegionsCasesDataUseCase {
            return UpdateCountryRegionsCasesDataUseCase(covid19CasesRepository)
    }

}