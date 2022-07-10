package com.akerimtay.rickandmorty.location.presentation.list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.akerimtay.rickandmorty.core.presentation.base.BaseFragment
import com.akerimtay.rickandmorty.core.presentation.util.extensions.launchWhenStarted
import com.akerimtay.rickandmorty.core.presentation.util.extensions.showToast
import com.akerimtay.rickandmorty.core.presentation.viewbinding.viewBinding
import com.akerimtay.rickandmorty.location.R
import com.akerimtay.rickandmorty.location.databinding.FragmentLocationsBinding
import com.akerimtay.rickandmorty.location.presentation.ComponentViewModel
import javax.inject.Inject

class LocationsFragment : BaseFragment(R.layout.fragment_locations) {

    @Inject
    internal lateinit var viewModelFactory: LocationsViewModelFactory

    private val viewBinding by viewBinding(FragmentLocationsBinding::bind)
    private val viewModel: LocationsViewModel by viewModels { viewModelFactory }
    private val componentViewModel: ComponentViewModel by viewModels()

    override fun onAttach(context: Context) {
        componentViewModel.component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(viewBinding) {
        viewModel.locationsCount.launchWhenStarted(viewLifecycleOwner) {
            showToast("Count: $it")
        }
    }
}