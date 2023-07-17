package com.airalo.assignment.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.airalo.assignment.R
import com.airalo.assignment.base.BaseFragment
import com.airalo.assignment.core.extensions.replaceFragment
import com.airalo.assignment.core.extensions.showToastMsg
import com.airalo.assignment.core.extensions.viewBinding
import com.airalo.assignment.databinding.FragmentHomeBinding
import com.airalo.assignment.ui.activity.model.MainViewModel
import com.airalo.assignment.ui.home.adapter.CountriesAdapter
import com.airalo.assignment.ui.home.model.HomeViewModel
import com.airalo.assignment.ui.packages.PackagesFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * The HomeFragment.kt
 */

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private val sharedViewModel: MainViewModel by activityViewModels()
    private lateinit var countriesAdapter: CountriesAdapter
    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initObservations()
        countriesAdapter = CountriesAdapter().also {
            it.onCountrySelectionListener { country ->
                sharedViewModel.keepSelectedCountry(country)
                replaceFragment(PackagesFragment())
            }

            it.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            binding.countriesRecyclerView.adapter = it
        }

        binding.editTextSearchView.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                viewModel.onSearchContact(s.toString())
            }
        })
    }

    private fun initObservations() {
        viewModel.uiStateLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoadingState -> {
                    progressDialog.show()
                }

                is ContentState -> {
                    progressDialog.dismiss()
                }

                is ErrorState -> {
                    showToastMsg(state.message)
                }
            }
        }

        viewModel.countriesLiveData.observe(viewLifecycleOwner) { countries ->
            countriesAdapter.setItems(countries)
        }
    }
}
