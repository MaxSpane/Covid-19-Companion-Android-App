package it.weMake.covid19Companion.di.modules.presentation

import dagger.Binds
import it.weMake.covid19Companion.di.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector
import it.weMake.covid19Companion.di.modules.presentation.fragmentBuilders.MainActivityFragmentBuilder
import it.weMake.covid19Companion.ui.landing.MainActivity
import it.weMake.covid19Companion.ui.preventionTips.PreventionTipsActivity


@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityFragmentBuilder::class])
    internal abstract fun provideMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun providePreventionTipsActivity(): PreventionTipsActivity

}