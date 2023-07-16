package com.airalo.assignment.data.remote

import com.airalo.assignment.data.model.ModelCountriesResponse
import com.airalo.assignment.data.model.ModelPackagesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET(Package_SERVICE_API)
    suspend fun fetchAvailablePackages(
        @Path("country") country: String,
    ): ApiResponse<ModelPackagesResponse>

    @GET(COUNTRIES_SERVICE_API)
    suspend fun fetchCountries(
        @Query("type") type: String = "popular",
    ): ApiResponse<List<ModelCountriesResponse>?>

    companion object {
        const val COUNTRIES_SERVICE_API = "countries"
        const val Package_SERVICE_API = "countries/{country}"
    }
}
