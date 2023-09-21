package com.codenablers.petadoption.utils

sealed interface Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>
    data class Error<T>(val message: String, val data: T? = null) : Resource<T>
   // data class Error(val exception: Throwable? = null) : Resource<Nothing>
    object Loading : Resource<Nothing>
}

/*
fun <T> Flow<T>.asResource(): Flow<Resource<T>> {
    return this
        .map<T, Resource<T>> {
            Resource.Success(it)
        }
        .onStart { emit(Resource.Loading) }
        .catch { emit(Resource.Error(it)) }
}*/
