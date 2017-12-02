package com.infotechincubator.sharmal.api

import com.infotechincubator.sharmal.Constants
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by kyawagwin on 29/11/17.
 */
@Module
class ApiModule {

    @Provides @Singleton
    fun provideApiService(retrofit: Retrofit): GithubApiService =
            retrofit.create(GithubApiService::class.java)

    @Provides @Singleton
    fun provideRetrofit(rxJava2CallAdapterFactory: RxJava2CallAdapterFactory, gsonConverterFactory: GsonConverterFactory): Retrofit =
            Retrofit.Builder()
                    .baseUrl(Constants.GITHUB_API_BASE_ENDPOINT)
                    .addCallAdapterFactory(rxJava2CallAdapterFactory)
                    .addConverterFactory(gsonConverterFactory)
                    .build()

    @Provides @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory =
            GsonConverterFactory.create()

    @Provides @Singleton
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory =
            RxJava2CallAdapterFactory.create()
}