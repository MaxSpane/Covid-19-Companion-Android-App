package it.weMake.covid19Companion.di.modules

import com.k0d4black.theforce.data.api.HttpClient
import com.k0d4black.theforce.data.api.LoggingInterceptor
import dagger.Module
import dagger.Provides
import it.wemake.covid19Companion.data.api.Covid19ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
open class StarWarsApiModule {

    @Provides
    open fun provideLoggingInterceptor(): HttpLoggingInterceptor = LoggingInterceptor.create()

    @Provides
    open fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return HttpClient.setupOkhttpClient(httpLoggingInterceptor)
    }

    @Singleton
    @Provides
    open fun provideStarWarsApi(retrofit: Retrofit): Covid19ApiService {
        return retrofit.create(Covid19ApiService::class.java)
    }

    @Singleton
    @Provides
    open fun provideRetrofit(okHttpClient: OkHttpClient, @Named("baseUrl") baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Named("baseUrl")
    open fun provideBaseUrl(): String = "https://swapi.co/api/"

}