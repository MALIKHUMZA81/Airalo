package com.airalo.assignment.ui.home.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airalo.assignment.data.DataState
import com.airalo.assignment.data.usecases.FetchCountriesUseCase
import com.airalo.assignment.ui.home.ContentState
import com.airalo.assignment.ui.home.ErrorState
import com.airalo.assignment.ui.home.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

/**
 * The HomeViewModel.kt, viewModel to perform the actions of the home screens and to manipulate the fetched data
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchCountriesUseCase: FetchCountriesUseCase,
) : ViewModel() {

    init {
        fetchCountries()
    }

    private val _query = MutableLiveData<String>()

    private var _uiState = MutableLiveData<HomeUiState>()
    var uiStateLiveData: LiveData<HomeUiState> = _uiState

    private var _uiModelCountryList = MutableLiveData<List<UIModelCountry>>()

    private fun fetchCountries() {
        viewModelScope.launch {
            fetchCountriesUseCase.invoke()
                .collect { dataState ->
                    when (dataState) {
                        is DataState.Success -> {
                            dataState.data.let {
                                _uiModelCountryList.value = it
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

    private val filteredData = Transformations.switchMap(_query) { filterable ->
        Transformations.map(_uiModelCountryList) { list ->
            if (filterable.isNotBlank()) {
                list.filter {
                    it.title.toLowerCase(Locale.getDefault()).contains(filterable)
                }
            } else {
                list
            }
        }
    }

    val countriesLiveData = MediatorLiveData<List<UIModelCountry>>().apply {
        addSource(_uiModelCountryList) { value -> this.setValue(value) }
        addSource(filteredData) { value -> this.setValue(value) }
    }

    fun onSearchContact(query: String) {
        if (query.length >= QUERY_THRESHOLD || query.isEmpty()) {
            _query.value = query.toLowerCase(Locale.getDefault()).trim()
        }
    }

    companion object {
        const val QUERY_THRESHOLD = 2
    }
}
