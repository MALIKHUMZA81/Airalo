package com.airalo.assignment.data.usecases

import com.airalo.assignment.data.repository.countries.CountriesRepository
import javax.inject.Inject

/**
 * A use-case to load the Countries.
 */
class FetchCountriesUseCase @Inject constructor(
    private val countriesRepository: CountriesRepository,
) {
    suspend operator fun invoke() = countriesRepository.fetchCountries()
}
