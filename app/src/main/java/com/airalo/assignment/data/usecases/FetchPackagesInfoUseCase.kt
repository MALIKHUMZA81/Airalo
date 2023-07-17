package com.airalo.assignment.data.usecases

import com.airalo.assignment.data.repository.companyinfo.PackagesInfoRepository
import javax.inject.Inject

/**
 * A use-case to load the CompanyInfo from Repo.
 */
class FetchPackagesInfoUseCase @Inject constructor(
    private val packagesInfoRepository: PackagesInfoRepository,
) {
    suspend operator fun invoke(country: String) =
        packagesInfoRepository.fetchAvailablePackages(country)
}
