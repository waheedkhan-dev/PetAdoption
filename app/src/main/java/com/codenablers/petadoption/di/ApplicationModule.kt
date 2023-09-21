package com.codenablers.petadoption.di

import android.content.Context
import com.codenablers.petadoption.PetAdoptionApp
import com.codenablers.petadoption.utils.Constants.API_KEY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideApplicationInstance(@ApplicationContext applicationContext: Context):
            PetAdoptionApp {
        return applicationContext as PetAdoptionApp
    }

    @Named("apiKey")
    @Singleton
    @Provides
    fun provideApiKey(): String = API_KEY
}