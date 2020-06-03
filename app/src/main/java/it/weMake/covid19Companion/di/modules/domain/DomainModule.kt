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
    ): GetWHOHandHygieneBrochureDownloadIdUseCase = GetWHOHandHygieneBrochureDownloadIdUseCase(sharedPreferencesRepository)

    @Provides
    fun provideSetWHOHandHygieneBrochureDownloadIdLastUpdatedUseCase(
        sharedPreferencesRepository: SharedPreferencesRepository
    ): SetWHOHandHygieneBrochureDownloadIdUseCase = SetWHOHandHygieneBrochureDownloadIdUseCase(sharedPreferencesRepository)

    @Provides
    fun provideGetPagedCountriesCasesDataCasesUseCase(
        covid19CasesRepository: Covid19CasesRepository
    ): GetPagedCountriesCasesDataUseCase = GetPagedCountriesCasesDataUseCase(covid19CasesRepository)

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
    fun provideGetAllCountriesCasesDataCasesUseCase(
        covid19CasesRepository: Covid19CasesRepository
    ): GetAllCountriesCasesDataUseCase = GetAllCountriesCasesDataUseCase(covid19CasesRepository)

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

    @Provides
    fun provideGetDailyMotivationUseCase(
        sharedPreferencesRepository: SharedPreferencesRepository
    ): GetDailyMotivationUseCase {
            return GetDailyMotivationUseCase(sharedPreferencesRepository)
    }

    @Provides
    fun provideSetDailyMotivationUseCase(
        sharedPreferencesRepository: SharedPreferencesRepository
    ): SetDailyMotivationUseCase {
            return SetDailyMotivationUseCase(sharedPreferencesRepository)
    }

    @Provides
    fun provideGetAppReleasesUseCase(
        appReleasesRepository: AppReleasesRepository
    ): GetAppReleasesUseCase {
            return GetAppReleasesUseCase(appReleasesRepository)
    }

    @Provides
    fun provideInsertAppReleaseUseCase(
        appReleasesRepository: AppReleasesRepository
    ): InsertAppReleaseUseCase {
            return InsertAppReleaseUseCase(appReleasesRepository)
    }

    @Provides
    fun provideGetLatestVersionCodeUseCase(
        sharedPreferencesRepository: SharedPreferencesRepository
    ): GetLatestVersionCodeUseCase {
        return GetLatestVersionCodeUseCase(sharedPreferencesRepository)
    }

    @Provides
    fun provideSetLatestVersionCodeUseCase(
        sharedPreferencesRepository: SharedPreferencesRepository
    ): SetLatestVersionCodeUseCase {
        return SetLatestVersionCodeUseCase(sharedPreferencesRepository)
    }

}