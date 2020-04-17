package it.weMake.covid19Companion.di

import android.content.Context
import it.weMake.covid19Companion.di.modules.presentation.ActivityBuilderModule
import it.weMake.covid19Companion.di.modules.remote.RetrofitModule
import it.weMake.covid19Companion.di.modules.presentation.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import it.weMake.covid19Companion.Application
import it.weMake.covid19Companion.di.modules.AppModule
import it.weMake.covid19Companion.di.modules.domain.DomainModule
import it.weMake.covid19Companion.di.modules.local.LocalModule
import it.weMake.covid19Companion.di.modules.local.RoomModule
import it.weMake.covid19Companion.di.modules.remote.RemoteModule
import it.weMake.covid19Companion.di.modules.repository.RepositoryModule
import it.weMake.covid19Companion.di.modules.services.ServiceModule
import javax.inject.Singleton

/**
 * Core Application Dagger Component
 */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        RemoteModule::class,
        LocalModule::class,
        RepositoryModule::class,
        DomainModule::class,
        ActivityBuilderModule::class,
        ViewModelModule::class,
        ServiceModule::class
    ]
)
interface AppComponent: AndroidInjector<Application> {

//    @Component.Factory
//    interface Factory {
//        fun create(@BindsInstance context: Context): AppComponent
//    }

    /**
     * A {@see [Component.Builder]} that initializes necessary implementations
     */
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application : Application) : Builder
        fun build() : AppComponent
    }

}

