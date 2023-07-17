package com.airalo.assignment.ui.packages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.airalo.assignment.R
import com.airalo.assignment.base.BaseFragment
import com.airalo.assignment.core.extensions.showToastMsg
import com.airalo.assignment.core.extensions.viewBinding
import com.airalo.assignment.databinding.FragmentPackagesBinding
import com.airalo.assignment.ui.activity.model.MainViewModel
import com.airalo.assignment.ui.packages.adapter.PackagesAdapter
import com.airalo.assignment.ui.packages.model.PackagesViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

/**
 * The PackagesFragment.kt
 */

@AndroidEntryPoint
class PackagesFragment : BaseFragment(R.layout.fragment_packages) {

    private val viewModel: PackagesViewModel by viewModels()
    private val sharedViewModel: MainViewModel by activityViewModels()
    private lateinit var packagesAdapter: PackagesAdapter
    private val binding by viewBinding(FragmentPackagesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharedViewModel.uiModelCountryLiveData.value?.title?.let {
            binding.tvCountryName.text = it
            viewModel.fetchAvailablePackages(it.toLowerCase(Locale.US))
        }

        packagesAdapter = PackagesAdapter().also {
            it.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            binding.packagesRecyclerView.adapter = it
        }

        initObservations()
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

        viewModel.packageListLiveData.observe(viewLifecycleOwner) { packageInfoList ->
            packagesAdapter.setItems(packageInfoList)
        }
    }
}
