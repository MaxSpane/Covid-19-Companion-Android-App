package it.weMake.covid19Companion.di.modules.local

import dagger.Binds
import dagger.Module
import it.wemake.covid19Companion.data.local.ICasesDataLocal
import it.wemake.covid19Companion.data.local.ICountriesLocal
import it.wemake.covid19Companion.data.local.ISharedPreferencesLocal
import it.wemake.covid19Companion.local.impl.CasesDataLocal
import it.wemake.covid19Companion.local.impl.CountriesLocal
import it.wemake.covid19Companion.local.impl.SharedPreferencesLocal

@Module(includes = [SharedPreferencesModule::class, RoomModule::class])
abstract class LocalModule {

    @Binds
    internal abstract fun bindCasesDataLocal(casesDataLocal: CasesDataLocal): ICasesDataLocal

    @Binds
    internal abstract fun bindSharedPreferencesLocal( sharedPreferencesLocal: SharedPreferencesLocal): ISharedPreferencesLocal

}