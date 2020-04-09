package it.weMake.covid19Companion.di.modules.local

import dagger.Binds
import dagger.Module
import it.wemake.covid19Companion.data.local.IAreasCasesDataLocal
import it.wemake.covid19Companion.data.local.ISharedPreferencesLocal
import it.wemake.covid19Companion.local.impl.AreasCasesDataLocal
import it.wemake.covid19Companion.local.impl.SharedPreferencesLocal

@Module(includes = [SharedPreferencesModule::class, RoomModule::class])
abstract class LocalModule {

    @Binds
    internal abstract fun bindCountryCasesLocal(areasCasesDataLocal: AreasCasesDataLocal): IAreasCasesDataLocal

    @Binds
    internal abstract fun bindSharedPreferencesLocal( sharedPreferencesLocal: SharedPreferencesLocal): ISharedPreferencesLocal

}