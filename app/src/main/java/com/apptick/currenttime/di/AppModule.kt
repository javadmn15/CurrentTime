package com.apptick.currenttime.di

import android.content.Context
import com.apptick.currenttime.data.MainRepository
import com.apptick.currenttime.data.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context:Context):Context
    {
        return context
    }

    @Provides
    @Singleton
    fun provideMainRepository(
        service: NetworkService
    ): MainRepository {
        return MainRepository(
            service
        )
    }





}