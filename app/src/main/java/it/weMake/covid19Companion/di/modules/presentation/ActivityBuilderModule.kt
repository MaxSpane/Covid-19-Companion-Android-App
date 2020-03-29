package it.weMake.covid19Companion.di.modules.presentation

import it.weMake.covid19Companion.di.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector
import it.weMake.covid19Companion.di.modules.presentation.fragmentBuilders.MainActivityFragmentBuilder
import it.weMake.covid19Companion.ui.MainActivity


@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityFragmentBuilder::class])
    internal abstract fun provideMainActivity(): MainActivity

}