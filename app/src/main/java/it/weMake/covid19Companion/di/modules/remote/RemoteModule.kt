package it.weMake.covid19Companion.di.modules.remote

import dagger.Binds
import dagger.Module
import it.wemake.covid19Companion.data.remote.ICharacterSearchRemote
import it.wemake.covid19Companion.remote.impl.StarWarsCharacterSearchRemote
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class])
abstract class RemoteModule {

    @Singleton
    @Binds
    abstract fun bindCharacterSearchRemote(
        starWarsCharacterSearchRemote: StarWarsCharacterSearchRemote
    ): ICharacterSearchRemote

}