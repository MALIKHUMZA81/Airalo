package com.airalo.assignment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.airalo.assignment.data.DataState
import com.airalo.assignment.data.usecases.FetchCountriesUseCase
import com.airalo.assignment.ui.home.HomeUiState
import com.airalo.assignment.ui.home.model.HomeViewModel
import com.airalo.assignment.ui.home.model.UIModelCountry
import com.airalo.assignment.utils.CountriesDataSource
import com.airalo.assignment.utils.TestCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CountriesViewModelTest {

    // Subject under test
    private lateinit var sut: HomeViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = TestCoroutineRule()

    @MockK
    lateinit var fetchCountriesUseCase: FetchCountriesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test when HomeViewModel is initialized, new countries are fetched`(): Unit = runBlocking {
        // Given
        val uIModelCountry = listOf(CountriesDataSource.uIModelCountry)
        val uiObserver = mockk<Observer<HomeUiState>>(relaxed = true)
        val countryListObserver = mockk<Observer<List<UIModelCountry>>>(relaxed = true)

        // When
        coEvery { fetchCountriesUseCase.invoke() }
            .returns(flowOf(DataState.success(uIModelCountry)))

        // Invoke
        sut = HomeViewModel(fetchCountriesUseCase)
        sut.uiStateLiveData.observeForever(uiObserver)
        sut.countriesLiveData.observeForever(countryListObserver)

        // Then
        coVerify(exactly = 1) { fetchCountriesUseCase.invoke() }
        verify { countryListObserver.onChanged(match { it == uIModelCountry }) }
    }
}
