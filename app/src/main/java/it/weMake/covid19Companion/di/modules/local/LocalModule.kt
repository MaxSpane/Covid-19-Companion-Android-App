package it.weMake.covid19Companion.di.modules.local

import dagger.Binds
import dagger.Module
import it.wemake.covid19Companion.data.local.*
import it.wemake.covid19Companion.local.impl.*

@Module(includes = [SharedPreferencesModule::class, RoomModule::class])
abstract class LocalModule {

    @Binds
    internal abstract fun bindCasesDataLocal(casesDataLocal: CasesDataLocal): ICasesDataLocal

    @Binds
    internal abstract fun bindSharedPreferencesLocal( sharedPreferencesLocal: SharedPreferencesLocal): ISharedPreferencesLocal

    @Binds
    internal abstract fun bindPreventionTipsLocal( preventionTipsLocal: PreventionTipsLocal): IPreventionTipsLocal

    @Binds
    internal abstract fun bindCountriesLocal( countriesLocal: CountriesLocal): ICountriesLocal

    @Binds
    internal abstract fun bindWashHandsReminderLocationsLocal( washHandsReminderLocationsLocal: WashHandsReminderLocationsLocal)
            : IWashHandsReminderLocationsLocal

    @Binds
    internal abstract fun bindAppReleasesLocal( appReleasesLocal: AppReleasesLocal)
            : IAppReleasesLocal

    @Binds
    internal abstract fun bindSourcesLocal( sourceLocal: SourcesLocal)
            : ISourcesLocal

}