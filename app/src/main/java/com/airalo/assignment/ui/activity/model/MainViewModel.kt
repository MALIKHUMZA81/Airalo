package com.airalo.assignment.ui.activity.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airalo.assignment.ui.home.model.UIModelCountry
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * The MainViewModel.kt, shared viewModel to transect the data b/w fragments
 */

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    // for the webView
    private var _uiModelCountryLiveData = MutableLiveData<UIModelCountry>()
    var uiModelCountryLiveData: LiveData<UIModelCountry> = _uiModelCountryLiveData

    fun keepSelectedCountry(uIModelCountry: UIModelCountry) {
        _uiModelCountryLiveData.value = uIModelCountry
    }
}
