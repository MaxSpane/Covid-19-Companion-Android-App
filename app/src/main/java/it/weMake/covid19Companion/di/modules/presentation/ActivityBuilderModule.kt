package it.weMake.covid19Companion.di.modules.presentation

import dagger.Binds
import it.weMake.covid19Companion.di.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector
import it.weMake.covid19Companion.di.modules.presentation.fragmentBuilders.MainActivityFragmentBuilder
import it.weMake.covid19Companion.di.modules.presentation.fragmentBuilders.ScreeningToolActivityFragmentBuilder
import it.weMake.covid19Companion.ui.landing.MainActivity
import it.weMake.covid19Companion.ui.landing.sortedDetailsData.SortedDetailsDataActivity
import it.weMake.covid19Companion.ui.preventionTips.PreventionTipsActivity
import it.weMake.covid19Companion.ui.regionalStats.RegionalStatsActivity
import it.weMake.covid19Companion.ui.screeningTool.ScreeningToolActivity
import it.weMake.covid19Companion.ui.splashscreen.SplashScreenActivity


@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityFragmentBuilder::class])
    internal abstract fun provideMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun providePreventionTipsActivity(): PreventionTipsActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ScreeningToolActivityFragmentBuilder::class])
    internal abstract fun provideScreeningToolActivity(): ScreeningToolActivity

      @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun provideSplashScreenActivity(): SplashScreenActivity

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun provideRegionalStatsActivity(): RegionalStatsActivity

      @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun provideSortedDetailsDataActivity(): SortedDetailsDataActivity

}