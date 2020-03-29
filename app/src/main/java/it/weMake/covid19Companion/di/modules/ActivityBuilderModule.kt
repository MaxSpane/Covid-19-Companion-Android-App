package it.weMake.covid19Companion.di.modules

import it.weMake.covid19Companion.di.ActivityScope
import it.weMake.covid19Companion.di.modules.search.CharacterSearchBindingModule
import it.weMake.covid19Companion.di.modules.search.CharacterSearchModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import it.weMake.covid19Companion.di.FragmentScope
import it.weMake.covid19Companion.ui.MainActivity
import it.weMake.covid19Companion.ui.dashboard.DashboardFragment


@Module(includes = [CharacterSearchBindingModule::class])
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [CharacterSearchModule::class])
    abstract fun provideMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [CharacterSearchModule::class])
    abstract fun provideDashboardFragment(): DashboardFragment

}