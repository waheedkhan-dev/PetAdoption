package com.codenablers.petadoption.di

import com.codenablers.petadoption.data.datasource.api.PetApi
import com.codenablers.petadoption.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesBaseUrl(): String {
        return Constants.BASE_URL
    }

    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ) = OkHttpClient.Builder().apply {
        addInterceptor(httpLoggingInterceptor)
        connectTimeout(2, TimeUnit.MINUTES)
        readTimeout(2, TimeUnit.MINUTES)
        writeTimeout(2, TimeUnit.MINUTES)
    }.build()

    @Singleton
    @Provides
    fun providesRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient
    ): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .client(okHttpClient)

        return retrofit.build()
    }

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): PetApi {
        return retrofit.create(PetApi::class.java)
    }
}