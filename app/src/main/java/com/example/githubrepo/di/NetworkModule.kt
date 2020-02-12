package com.example.githubrepo.di

import com.example.githubrepo.data.remote.TrendingRepoService
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class NetworkModule {

    companion object {
        const val BASE_URL = "https://github-trending-api.now.sh/"
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun provideTrendingRepoService(okHttpClient: OkHttpClient): TrendingRepoService {

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
            .create(TrendingRepoService::class.java)
    }

    @Provides
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO

}