package it.weMake.covid19Companion.di.modules.local

import dagger.Binds
import dagger.Module
import it.wemake.covid19Companion.data.local.ICountryCasesLocal
import it.wemake.covid19Companion.data.local.ISharedPreferencesLocal
import it.wemake.covid19Companion.local.impl.CountryCasesLocal
import it.wemake.covid19Companion.local.impl.SharedPreferencesLocal

@Module(includes = [SharedPreferencesModule::class, RoomModule::class])
abstract class LocalModule {

    @Binds
    internal abstract fun bindCountryCasesLocal(countryCasesLocal: CountryCasesLocal): ICountryCasesLocal

    @Binds
    internal abstract fun bindSharedPreferencesLocal( sharedPreferencesLocal: SharedPreferencesLocal): ISharedPreferencesLocal

}