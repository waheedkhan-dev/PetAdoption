package com.codenablers.petadoption.presentation.screens.pet

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.codenablers.petadoption.data.repository.PetsRepositoryImpl
import com.codenablers.petadoption.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@OptIn(ExperimentalCoroutinesApi::class)
class PetViewModelTest {

    private val repository = mockk<PetsRepositoryImpl>(relaxed = true)
    private lateinit var viewModel: PetViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        viewModel = PetViewModel(repository, "")
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun `Test loading state is shown before the content`() = runTest {
        coEvery { repository.getPrettyPets(any(), any(), any(), true) } returns flow {
            emit(
                Resource.Loading
            )
        }
        viewModel.getPrettyPets("", "", true)
        dispatcher.scheduler.advanceUntilIdle()
        assert(viewModel.petsUiState.value is PetsUiState.Loading)
    }

    @Test
    fun `Test success state is shown after the loading`() = runTest {
        coEvery { repository.getPrettyPets(any(), any(), any(), true) } returns flow {
            emit(
                Resource.Success(mockk())
            )
        }
        viewModel.getPrettyPets("", "", true)
        dispatcher.scheduler.advanceUntilIdle()
        assert(viewModel.petsUiState.value is PetsUiState.Success)

    }
}