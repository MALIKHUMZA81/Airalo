package com.airalo.assignment.ui.packages.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airalo.assignment.data.DataState
import com.airalo.assignment.data.usecases.FetchPackagesInfoUseCase
import com.airalo.assignment.ui.packages.ContentState
import com.airalo.assignment.ui.packages.ErrorState
import com.airalo.assignment.ui.packages.PackagesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The PackagesViewModel.kt, viewModel to perform the actions of the home screens and to manipulate the fetched data
 */

@HiltViewModel
class PackagesViewModel @Inject constructor(
    private val fetchPackagesInfoUseCase: FetchPackagesInfoUseCase,
) : ViewModel() {

    private var _uiState = MutableLiveData<PackagesUiState>()
    var uiStateLiveData: LiveData<PackagesUiState> = _uiState

    private var _uiModelPackageList = MutableLiveData<List<UIModelPackageInfo>>()
    var packageListLiveData: LiveData<List<UIModelPackageInfo>> = _uiModelPackageList

    fun fetchAvailablePackages(country: String) {
        viewModelScope.launch {
            fetchPackagesInfoUseCase.invoke(country = country)
                .collect { dataState ->
                    when (dataState) {
                        is DataState.Success -> {
                            dataState.data.let {
                                _uiModelPackageList.value = it
                            }
                        }

                        is DataState.Error -> {
                            _uiState.postValue(ErrorState(dataState.message))
                        }
                    }
                }
            _uiState.postValue(ContentState)
        }
    }
}
