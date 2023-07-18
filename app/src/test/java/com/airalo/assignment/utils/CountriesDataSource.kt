package com.airalo.assignment.utils

import com.airalo.assignment.data.model.ModelCountriesResponse
import com.airalo.assignment.ui.home.model.UIModelCountry

object CountriesDataSource {

    fun getModelCountriesResponse(): ModelCountriesResponse {
        return ModelCountriesResponse(
            id = 1,
            title = "Title",
            image = ModelCountriesResponse.Image(url = "image_url"),
            packageCount = 1,
            slug = "slug",
        )
    }

    val uIModelCountry =
        UIModelCountry(id = 1, flagImage = "image_url", title = "Title", packageCount = 1)
}
