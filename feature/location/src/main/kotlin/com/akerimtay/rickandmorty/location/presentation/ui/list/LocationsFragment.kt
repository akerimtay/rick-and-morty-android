package com.akerimtay.rickandmorty.location.presentation.ui.list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.paging.CombinedLoadStates
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akerimtay.rickandmorty.core.presentation.LoadStateAdapter
import com.akerimtay.rickandmorty.core.presentation.base.BaseFragment
import com.akerimtay.rickandmorty.core.presentation.util.DefaultItemDecorator
import com.akerimtay.rickandmorty.core.presentation.util.extensions.launchWhenStarted
import com.akerimtay.rickandmorty.core.presentation.util.extensions.px
import com.akerimtay.rickandmorty.core.presentation.util.extensions.setOnSafeClickListener
import com.akerimtay.rickandmorty.core.presentation.viewbinding.viewBinding
import com.akerimtay.rickandmorty.location.R
import com.akerimtay.rickandmorty.location.databinding.FragmentLocationsBinding
import com.akerimtay.rickandmorty.location.presentation.ui.ComponentViewModel
import com.akerimtay.rickandmorty.location.presentation.ui.list.adapter.LocationAdapter
import javax.inject.Inject
import kotlinx.coroutines.flow.onEach

class LocationsFragment : BaseFragment(R.layout.fragment_locations) {

    @Inject
    internal lateinit var viewModelFactory: LocationsViewModelFactory

    private val viewBinding by viewBinding(FragmentLocationsBinding::bind)
    private val viewModel: LocationsViewModel by viewModels { viewModelFactory }
    private val componentViewModel: ComponentViewModel by viewModels()

    private val loadStateListener = { states: CombinedLoadStates -> viewModel.onLoadStates(states) }

    override fun onAttach(context: Context) {
        componentViewModel.component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(viewBinding) {
        val locationAdapter = LocationAdapter().apply {
            val loadStateAdapter = LoadStateAdapter(::retry)
            addLoadStateListener(loadStateListener)
            withLoadStateFooter(loadStateAdapter)
        }
        rvLocations.adapter = locationAdapter
        rvLocations.addItemDecoration(DefaultItemDecorator(divider = DIVIDER_SIZE.px))
        rvLocations.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val position = (rvLocations.layoutManager as? LinearLayoutManager)?.findFirstVisibleItemPosition()
                viewModel.onScrolled(position)
            }
        })

        srlLocations.setOnRefreshListener { locationAdapter.refresh() }
        fabScrollTop.setOnSafeClickListener { viewModel.onScrollToTopClicked() }

        viewModel.actions
            .onEach { action ->
                when (action) {
                    is LocationsAction.ScrollToPosition -> rvLocations.scrollToPosition(action.position)
                }
            }
            .launchWhenStarted(viewLifecycleOwner)
        viewModel.isSwipeRefreshing.launchWhenStarted(viewLifecycleOwner) { srlLocations.isRefreshing = it }
        viewModel.locationsCount.launchWhenStarted(viewLifecycleOwner) { count ->
            tvLocationCount.text = getString(R.string.locations_count_format, count)
        }
        viewModel.items.launchWhenStarted(viewLifecycleOwner) { locationAdapter.submitData(it) }
        viewModel.isScrollToTopVisible.launchWhenStarted(viewLifecycleOwner) { fabScrollTop.isVisible = it }
    }

    companion object {

        private const val DIVIDER_SIZE = 24
    }
}