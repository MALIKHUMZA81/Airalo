package com.airalo.assignment

import com.airalo.assignment.data.mappers.CountriesMapper.toUIModelCountriesList
import com.airalo.assignment.utils.CountriesDataSource
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CountriesMapperTest {

    @Test
    fun `given CountriesMapper when the toUIModelCountriesList in called then it return the expected results`() {
        // Given
        val modelCountriesResponses = arrayListOf(CountriesDataSource.getModelCountriesResponse())

        // When
        val uiModelCountries = modelCountriesResponses.toUIModelCountriesList()

        // then
        modelCountriesResponses.forEachIndexed { index, givenItem ->
            assertEquals(givenItem.id, uiModelCountries[index].id)
            assertEquals(givenItem.title, uiModelCountries[index].title)
            assertEquals(givenItem.packageCount, uiModelCountries[index].packageCount)
            assertEquals(givenItem.image?.url, uiModelCountries[index].flagImage)
        }
    }
}
