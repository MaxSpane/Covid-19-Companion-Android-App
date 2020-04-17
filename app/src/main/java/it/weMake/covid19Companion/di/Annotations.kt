package it.weMake.covid19Companion.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import javax.inject.Scope
import kotlin.reflect.KClass

/**
 * Annotation for having custom keys for viewmodel factory map
 */
@MapKey
@Target(AnnotationTarget.FUNCTION)
annotation class ViewModelKey(val value: KClass<out ViewModel>)

/**
 * Scopes dependencies injected into activity to flag_be preserved flag_as long flag_as the activity component is available
 */
@Scope
annotation class ActivityScope

/**
 * Scopes dependencies injected into fragment to flag_be preserved flag_as long flag_as the fragment component is available
 */
@Scope
annotation class FragmentScope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerService