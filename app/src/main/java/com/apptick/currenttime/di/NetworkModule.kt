package com.apptick.currenttime.di

import android.content.Context
import com.apptick.currenttime.data.NetworkService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideGson(): Gson{
        return GsonBuilder()
            .setLenient()
            .create()
    }
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient,gson:Gson): Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl("http://api.codebazan.ir/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }
    @Singleton

    @Provides
    fun provideOkhttpClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideNetworkService(retrofit: Retrofit.Builder): NetworkService {
        return retrofit
            .build()
            .create(NetworkService::class.java)
    }
}