package it.weMake.covid19Companion.di.modules.remote

import dagger.Binds
import dagger.Module
import it.wemake.covid19Companion.data.remote.ICovid19CasesRemote
import it.wemake.covid19Companion.data.remote.IScreeningToolRemote
import it.wemake.covid19Companion.remote.impl.Covid19CasesRemote
import it.wemake.covid19Companion.remote.impl.ScreeningToolRemote
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class])
abstract class RemoteModule {

    @Singleton
    @Binds
    abstract fun bindCovid19CasesRemote(
        covid19CasesRemote: Covid19CasesRemote
    ): ICovid19CasesRemote

    @Singleton
    @Binds
    abstract fun bindScreeningToolRemote(
        screeningToolRemote: ScreeningToolRemote
    ): IScreeningToolRemote

}