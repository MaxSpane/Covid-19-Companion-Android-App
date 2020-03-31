package it.weMake.covid19Companion.di.modules.local

import dagger.Binds
import dagger.Module
import it.wemake.covid19Companion.data.local.ICountryLocal
import it.wemake.covid19Companion.data.local.ISharedPreferencesLocal
import it.wemake.covid19Companion.local.impl.CountryLocal
import it.wemake.covid19Companion.local.impl.SharedPreferencesLocal

@Module(includes = [SharedPreferencesModule::class])
abstract class LocalModule {

    @Binds
    internal abstract fun bindCountryLocal( countryLocal: CountryLocal): ICountryLocal

    @Binds
    internal abstract fun bindSharedPreferencesLocal( sharedPreferencesLocal: SharedPreferencesLocal): ISharedPreferencesLocal

}