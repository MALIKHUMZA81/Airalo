package com.airalo.assignment

import com.airalo.assignment.data.DataState
import com.airalo.assignment.data.repository.countries.CountriesRepository
import com.airalo.assignment.data.usecases.FetchCountriesUseCase
import com.airalo.assignment.utils.CountriesDataSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FetchCountriesUseCaseTest {

    @MockK
    private lateinit var repository: CountriesRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `given when the invoke in FetchCountriesUseCase is called then it fetch the countries`() =
        runBlocking {
            // Given
            val sut = FetchCountriesUseCase(repository)
            val uiModelCountryList = listOf(CountriesDataSource.uIModelCountry)

            // When
            coEvery { repository.fetchCountries() }
                .returns(flowOf(DataState.success(uiModelCountryList)))

            // Invoke
            val countryListFlow = sut()

            // Then
            MatcherAssert.assertThat(countryListFlow, CoreMatchers.notNullValue())

            val countryListDataState = countryListFlow.first()
            MatcherAssert.assertThat(countryListDataState, CoreMatchers.notNullValue())
            MatcherAssert.assertThat(
                countryListDataState,
                CoreMatchers.instanceOf(DataState.Success::class.java),
            )

            val countries = (countryListDataState as DataState.Success).data
            MatcherAssert.assertThat(countries[0].id, `is`(uiModelCountryList[0].id))
            MatcherAssert.assertThat(countries[0].title, `is`(uiModelCountryList[0].title))
            MatcherAssert.assertThat(countries[0].flagImage, `is`(uiModelCountryList[0].flagImage))
            MatcherAssert.assertThat(countries[0].packageCount, `is`(uiModelCountryList[0].packageCount))
        }
}
