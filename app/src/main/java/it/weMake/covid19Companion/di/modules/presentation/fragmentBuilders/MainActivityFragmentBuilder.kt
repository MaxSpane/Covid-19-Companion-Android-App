package it.weMake.covid19Companion.di.modules.presentation.fragmentBuilders

import dagger.Module
import dagger.android.ContributesAndroidInjector
import it.weMake.covid19Companion.di.FragmentScope
import it.weMake.covid19Companion.ui.dashboard.DashboardFragment

@Module
abstract class MainActivityFragmentBuilder {

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun provideDashboardFragment(): DashboardFragment

}