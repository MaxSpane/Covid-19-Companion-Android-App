package it.weMake.covid19Companion

import android.app.Application
import it.weMake.covid19Companion.di.AppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import it.weMake.covid19Companion.di.DaggerAppComponent
import javax.inject.Inject

open class Application : DaggerApplication(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    lateinit var appInjector: AndroidInjector<out DaggerApplication>

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
//        appComponent = DaggerAppComponent.factory().create(applicationContext)
//
//        getApplicationComponent().inject(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)

        return appComponent
    }

    open fun getApplicationComponent(): AppComponent = appComponent

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

}