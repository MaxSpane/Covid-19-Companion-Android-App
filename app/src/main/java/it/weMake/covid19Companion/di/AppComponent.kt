package it.weMake.covid19Companion.di

import android.content.Context
import it.weMake.covid19Companion.di.modules.presentation.ActivityBuilderModule
import it.weMake.covid19Companion.di.modules.remote.RetrofitModule
import it.weMake.covid19Companion.di.modules.presentation.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import it.weMake.covid19Companion.Application
import it.weMake.covid19Companion.di.modules.domain.DomainModule
import it.weMake.covid19Companion.di.modules.remote.RemoteModule
import it.weMake.covid19Companion.di.modules.repository.RepositoryModule
import javax.inject.Singleton

/**
 * Core Application Dagger Component
 */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        RemoteModule::class,
        RepositoryModule::class,
        DomainModule::class,
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

