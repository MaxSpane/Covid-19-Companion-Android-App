package it.weMake.covid19Companion.di.modules.local

import dagger.Binds
import dagger.Module
import it.wemake.covid19Companion.data.local.ICountryLocal
import it.wemake.covid19Companion.local.impl.CountryLocal

@Module
abstract class LocalModule {

    @Binds
    internal abstract fun bindCountryLocal( countryLocal: CountryLocal): ICountryLocal

}