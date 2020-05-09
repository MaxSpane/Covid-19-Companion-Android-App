package it.weMake.covid19Companion.di.modules.presentation.fragmentBuilders

import dagger.Module
import dagger.android.ContributesAndroidInjector
import it.weMake.covid19Companion.di.FragmentScope
import it.weMake.covid19Companion.ui.landing.dashboard.DashboardFragment
import it.weMake.covid19Companion.ui.landing.help.HelpFragment
import it.weMake.covid19Companion.ui.screeningTool.fragments.DiagnosisFragment
import it.weMake.covid19Companion.ui.screeningTool.fragments.InitialQuestionsFragment
import it.weMake.covid19Companion.ui.screeningTool.fragments.QuestionFragment

@Module
abstract class ScreeningToolActivityFragmentBuilder {

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun provideInitialQuestionsFragment(): InitialQuestionsFragment

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun provideQuestionFragment(): QuestionFragment

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun provideResultFragment(): DiagnosisFragment

}