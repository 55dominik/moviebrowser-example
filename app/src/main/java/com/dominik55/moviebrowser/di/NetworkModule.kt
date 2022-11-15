package com.dominik55.moviebrowser.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.dominik55.moviebrowser.R
import com.dominik55.moviebrowser.data.network.ApiKeyInterceptor
import com.dominik55.moviebrowser.data.network.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    internal fun provideOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
            )
            .addInterceptor(ApiKeyInterceptor())
            .build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(
        @ApplicationContext context: Context,
        gson: Gson,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(context.resources.getString(R.string.api_base_url))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideNumberApiService(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }
}