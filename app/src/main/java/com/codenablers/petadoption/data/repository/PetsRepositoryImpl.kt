package com.codenablers.petadoption.data.repository

import com.codenablers.petadoption.data.datasource.api.PetApi
import com.codenablers.petadoption.domain.datamodel.Pets
import com.codenablers.petadoption.domain.repository.PetsRepository
import com.codenablers.petadoption.utils.Resource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PetsRepositoryImpl @Inject constructor(private val petApi: PetApi) : PetsRepository {

    override suspend fun getPrettyPets(
        key: String,
        query: String,
        imageType: String,
        pretty: Boolean
    ): Flow<Resource<Pets>> {
        return flow {
            try {
                emit(Resource.Loading)
                val response = petApi.getPrettyPets(key, query, imageType, pretty)
                if (response.isSuccessful) {
                    emit(Resource.Success(response.body()!!))
                } else {
                    emit(Resource.Error(response.message()))
                }
            } catch (ex: IOException) {
                emit(Resource.Error("No internet connection"))
            } catch (httpEx: HttpException) {
                emit(Resource.Error("Something went wrong!"))
            }
        }.flowOn(IO)
    }
}