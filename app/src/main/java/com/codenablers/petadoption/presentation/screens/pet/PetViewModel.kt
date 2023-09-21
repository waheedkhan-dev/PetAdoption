package com.codenablers.petadoption.presentation.screens.pet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codenablers.petadoption.data.repository.PetsRepositoryImpl
import com.codenablers.petadoption.domain.datamodel.Pets
import com.codenablers.petadoption.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class PetViewModel @Inject constructor(
    private val petsRepositoryImpl: PetsRepositoryImpl,
    @Named("apiKey") private val apiKey: String,
) :
    ViewModel() {

    private val _petsUiState = MutableStateFlow<PetsUiState>(PetsUiState.OnEmpty)
    var petsUiState: StateFlow<PetsUiState> = _petsUiState.asStateFlow()

    fun getPrettyPets(query: String, imageType: String, isPretty: Boolean) {
        viewModelScope.launch {
            petsRepositoryImpl.getPrettyPets(apiKey, query, imageType, isPretty)
                .collect { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            _petsUiState.emit(PetsUiState.Loading)
                        }

                        is Resource.Success -> {
                            _petsUiState.emit(PetsUiState.Success(resource.data))
                        }

                        is Resource.Error -> {
                            _petsUiState.emit(PetsUiState.Error(resource.message))
                        }
                    }
                }
        }
    }

}

sealed interface PetsUiState {
    data class Success(val prettyPets: Pets) : PetsUiState
    data class Error(val errorMessage: String) : PetsUiState
    object Loading : PetsUiState
    object OnEmpty : PetsUiState
}