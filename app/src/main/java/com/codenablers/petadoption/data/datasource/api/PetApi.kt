package com.codenablers.petadoption.data.datasource.api

import com.codenablers.petadoption.domain.datamodel.Pets
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PetApi {

    @GET("api/")
    suspend fun getPrettyPets(
        @Query("key") key: String,
        @Query("q") query: String,
        @Query("image_type") imageType: String,
        @Query("pretty") pretty : Boolean
    ): Response<Pets>
}