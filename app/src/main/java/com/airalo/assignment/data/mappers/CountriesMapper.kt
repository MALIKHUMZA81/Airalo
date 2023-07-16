package com.airalo.assignment.data.mappers

import com.airalo.assignment.data.model.ModelCountriesResponse
import com.airalo.assignment.ui.home.model.UIModelCountry

object CountriesMapper {

    fun List<ModelCountriesResponse>?.toUIModelCountriesList(): List<UIModelCountry> {
        val countries = this ?: emptyList()

        return countries.map {
            UIModelCountry(
                id = it.id,
                flagImage = it.image?.url,
                title = it.title,
                packageCount = it.packageCount,
            )
        }
    }
}
