package it.weMake.covid19Companion.di.modules.presentation.fragmentBuilders

import dagger.Module
import dagger.android.ContributesAndroidInjector
import it.weMake.covid19Companion.di.FragmentScope
import it.weMake.covid19Companion.ui.landing.dashboard.DashboardFragment
import it.weMake.covid19Companion.ui.landing.help.HelpFragment

@Module
abstract class MainActivityFragmentBuilder {

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun provideDashboardFragment(): DashboardFragment

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun provideHelpFragment(): HelpFragment

}