package it.weMake.covid19Companion.di

import android.content.Context
import it.weMake.covid19Companion.di.modules.ActivityBuilderModule
import it.weMake.covid19Companion.di.modules.StarWarsApiModule
import it.weMake.covid19Companion.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import it.weMake.covid19Companion.Application
import javax.inject.Singleton

/**
 * Core Application Dagger Component
 */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        StarWarsApiModule::class,
        ActivityBuilderModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    fun inject(app: Application)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

}

