package com.codenablers.petadoption.domain.repository

import com.codenablers.petadoption.domain.datamodel.Pets
import com.codenablers.petadoption.utils.Resource
import kotlinx.coroutines.flow.Flow


interface PetsRepository {

    suspend fun getPrettyPets(
        key: String,
        query: String,
        image_type: String,
        pretty: Boolean
    ): Flow<Resource<Pets>>
}