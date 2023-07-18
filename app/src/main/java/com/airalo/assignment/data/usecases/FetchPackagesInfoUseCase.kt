package com.airalo.assignment.data.usecases

import com.airalo.assignment.data.repository.packageinfo.PackagesInfoRepository
import javax.inject.Inject

/**
 * A use-case to load the Packages from Repo.
 */
class FetchPackagesInfoUseCase @Inject constructor(
    private val packagesInfoRepository: PackagesInfoRepository,
) {
    suspend operator fun invoke(country: String) =
        packagesInfoRepository.fetchAvailablePackages(country)
}
