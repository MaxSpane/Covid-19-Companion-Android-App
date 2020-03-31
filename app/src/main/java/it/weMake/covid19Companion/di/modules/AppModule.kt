package it.weMake.covid19Companion.di.modules

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import it.weMake.covid19Companion.Application
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule{

    @Singleton
    @Provides
    @Named("AppContext")
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

}