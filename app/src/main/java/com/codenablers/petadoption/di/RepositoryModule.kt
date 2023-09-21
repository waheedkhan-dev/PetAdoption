package com.codenablers.petadoption.di

import com.codenablers.petadoption.data.datasource.api.PetApi
import com.codenablers.petadoption.data.repository.PetsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePetRepository(
        petApi: PetApi

    ): PetsRepositoryImpl {
        return PetsRepositoryImpl(petApi = petApi)
    }

}