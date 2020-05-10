package it.weMake.covid19Companion.di.modules.presentation.fragmentBuilders

import dagger.Module
import dagger.android.ContributesAndroidInjector
import it.weMake.covid19Companion.di.FragmentScope
import it.weMake.covid19Companion.ui.landing.dashboard.DashboardFragment
import it.weMake.covid19Companion.ui.landing.help.HelpFragment
import it.weMake.covid19Companion.ui.landing.settings.SettingsFragment

@Module
abstract class MainActivityFragmentBuilder {

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun provideDashboardFragment(): DashboardFragment

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun provideHelpFragment(): HelpFragment

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun provideSettingsFragment(): SettingsFragment

}