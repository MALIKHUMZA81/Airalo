package com.airalo.assignment.data.repository.packageinfo

import androidx.annotation.WorkerThread
import com.airalo.assignment.core.extensions.noNetworkErrorMessage
import com.airalo.assignment.core.extensions.somethingWentWrong
import com.airalo.assignment.data.DataState
import com.airalo.assignment.data.mappers.PackageInfoMapper.toUIModelPackageInfo
import com.airalo.assignment.data.remote.ApiInterface
import com.airalo.assignment.data.remote.message
import com.airalo.assignment.data.remote.onErrorSuspend
import com.airalo.assignment.data.remote.onExceptionSuspend
import com.airalo.assignment.data.remote.onSuccessSuspend
import com.airalo.assignment.ui.packages.model.UIModelPackageInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

/**
 * [PackagesInfoRepository] is an interface data layer to handle communication with any data source such as Server
 * @see [PackagesInfoRepositoryImpl] for implementation of this class to utilize API.
 */
interface PackagesInfoRepository {
    suspend fun fetchAvailablePackages(country: String):
        Flow<DataState<List<UIModelPackageInfo>>>
}

/**
 * This is an implementation of [PackagesInfoRepository] to handle communication with [ApiInterface] server.
 */
class PackagesInfoRepositoryImpl @Inject constructor(
    private val apiService: ApiInterface,
) : PackagesInfoRepository {

    @WorkerThread
    override suspend fun fetchAvailablePackages(country: String): Flow<DataState<List<UIModelPackageInfo>>> {
        return flow {
            apiService.fetchAvailablePackages(country = country).apply {
                this.onSuccessSuspend {
                    data?.let {
                        emit(DataState.success(it.toUIModelPackageInfo()))
                    }
                }
                // handle the case when the API request gets an error response.
                // e.g. internal server error.
            }.onErrorSuspend {
                emit(DataState.error<List<UIModelPackageInfo>>(message()))
                // handle the case when the API request gets an exception response.
                // e.g. network connection error.
            }.onExceptionSuspend {
                if (this.exception is IOException) {
                    emit(DataState.error<List<UIModelPackageInfo>>(noNetworkErrorMessage()))
                } else {
                    emit(DataState.error<List<UIModelPackageInfo>>(somethingWentWrong()))
                }
            }
        }
    }
}
