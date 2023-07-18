package com.airalo.assignment.di

import com.airalo.assignment.data.remote.ApiInterface
import com.airalo.assignment.data.repository.packageinfo.PackagesInfoRepository
import com.airalo.assignment.data.repository.packageinfo.PackagesInfoRepositoryImpl
import com.airalo.assignment.data.repository.countries.CountriesRepository
import com.airalo.assignment.data.repository.countries.CountriesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * The Dagger Module for providing repository instances.
 */
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideCountriesRepository(apiService: ApiInterface): CountriesRepository {
        return CountriesRepositoryImpl(apiService)
    }

    @Singleton
    @Provides
    fun providePackagesInfoRepository(apiService: ApiInterface): PackagesInfoRepository {
        return PackagesInfoRepositoryImpl(apiService)
    }
}
