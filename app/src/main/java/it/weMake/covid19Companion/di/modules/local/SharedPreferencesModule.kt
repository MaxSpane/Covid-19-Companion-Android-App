package it.weMake.covid19Companion.di.modules.local

import android.content.Context
import dagger.Module
import dagger.Provides
import it.wemake.covid19Companion.local.preference.Covid19CompanionSharedPreferences
import javax.inject.Named

@Module
class SharedPreferencesModule {

    @Provides
    fun provideSharedPreferences(@Named("AppContext") applicationContext: Context) = Covid19CompanionSharedPreferences(applicationContext)

}