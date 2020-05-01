package it.weMake.covid19Companion.di.modules.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import it.weMake.covid19Companion.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import it.weMake.covid19Companion.di.factory.ViewModelFactory
import it.weMake.covid19Companion.ui.landing.dashboard.DashboardViewModel
import it.weMake.covid19Companion.ui.landing.help.HelpViewModel

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @Binds
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindDashboardViewModel(dashboardViewModel: DashboardViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(HelpViewModel::class)
    abstract fun bindHelpViewModel(helpViewModel: HelpViewModel): ViewModel

}