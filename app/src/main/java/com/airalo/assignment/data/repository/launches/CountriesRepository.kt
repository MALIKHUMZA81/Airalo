package com.airalo.assignment.data.repository.launches

import androidx.annotation.WorkerThread
import com.airalo.assignment.core.extensions.noNetworkErrorMessage
import com.airalo.assignment.core.extensions.somethingWentWrong
import com.airalo.assignment.data.DataState
import com.airalo.assignment.data.mappers.CountriesMapper.toUIModelCountriesList
import com.airalo.assignment.data.remote.ApiInterface
import com.airalo.assignment.data.remote.message
import com.airalo.assignment.data.remote.onErrorSuspend
import com.airalo.assignment.data.remote.onExceptionSuspend
import com.airalo.assignment.data.remote.onSuccessSuspend
import com.airalo.assignment.ui.home.model.UIModelCountry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

/**
 * CountriesRepository is an interface data layer to handle communication with any data source such as Server.
 * @see [CountriesRepositoryImpl] for implementation of this class to utilize API.
 */
interface CountriesRepository {
    suspend fun fetchCountries():
        Flow<DataState<List<UIModelCountry>>>
}

/**
 * This is an implementation of [CountriesRepository] to handle communication with [ApiInterface] server.
 */
class CountriesRepositoryImpl @Inject constructor(
    private val apiService: ApiInterface,
) : CountriesRepository {

    @WorkerThread
    override suspend fun fetchCountries(): Flow<DataState<List<UIModelCountry>>> {
        return flow {
            apiService.fetchCountries().apply {
                this.onSuccessSuspend {
                    data?.let {
                        emit(DataState.success(it.toUIModelCountriesList()))
                    }
                }
                // handle the case when the API request gets an error response.
                // e.g. internal server error.
            }.onErrorSuspend {
                emit(DataState.error<List<UIModelCountry>>(message()))
                // handle the case when the API request gets an exception response.
                // e.g. network connection error.
            }.onExceptionSuspend {
                if (this.exception is IOException) {
                    emit(DataState.error<List<UIModelCountry>>(noNetworkErrorMessage()))
                } else {
                    emit(DataState.error<List<UIModelCountry>>(somethingWentWrong()))
                }
            }
        }
    }
}
