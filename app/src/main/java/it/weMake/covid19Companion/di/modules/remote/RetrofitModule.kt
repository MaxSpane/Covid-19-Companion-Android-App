package it.weMake.covid19Companion.di.modules.remote

import dagger.Module
import dagger.Provides
import it.wemake.covid19Companion.remote.api.NovelCovidApiService
import it.wemake.covid19Companion.remote.api.HttpClient
import it.wemake.covid19Companion.remote.api.LoggingInterceptor
import it.wemake.covid19Companion.remote.utils.BASE_URL_NOVEL_COVID_19
import it.wemake.covid19Companion.remote.utils.RETROFIT_NOVEL_COVID_19
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
open class RetrofitModule {

    @Provides
    open fun provideLoggingInterceptor(): HttpLoggingInterceptor = LoggingInterceptor.create()

    @Provides
    open fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return HttpClient.setupOkhttpClient(httpLoggingInterceptor)
    }

    @Singleton
    @Provides
    @Named(RETROFIT_NOVEL_COVID_19)
    open fun provideNovelCovid19Retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_NOVEL_COVID_19)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    open fun provideNovelCovid19Api(@Named(RETROFIT_NOVEL_COVID_19) retrofit: Retrofit): NovelCovidApiService {
        return retrofit.create(NovelCovidApiService::class.java)
    }

}