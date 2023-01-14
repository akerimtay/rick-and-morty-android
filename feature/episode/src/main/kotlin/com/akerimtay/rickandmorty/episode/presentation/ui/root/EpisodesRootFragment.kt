package com.akerimtay.rickandmorty.episode.presentation.ui.root

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.akerimtay.rickandmorty.core.presentation.base.BaseFragment
import com.akerimtay.rickandmorty.core.presentation.viewbinding.viewBinding
import com.akerimtay.rickandmorty.episode.R
import com.akerimtay.rickandmorty.episode.databinding.FragmentEpisodesRootBinding
import com.akerimtay.rickandmorty.episode.presentation.ui.ComponentViewModel
import com.google.android.material.tabs.TabLayoutMediator

class EpisodesRootFragment : BaseFragment(R.layout.fragment_episodes_root) {

    private val viewBinding by viewBinding(FragmentEpisodesRootBinding::bind)
    private val viewModel: EpisodesRootViewModel by viewModels()
    private val componentViewModel: ComponentViewModel by viewModels()

    override fun onAttach(context: Context) {
        componentViewModel.component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(viewBinding) {
        super.onViewCreated(view, savedInstanceState)
        slvEpisodes.onSearchClickListener = { viewModel.onSearchClicked() }
        slvEpisodes.onViewClickListener = { viewModel.onSearchClicked() }

        val pagerAdapter = PagerAdapter(this@EpisodesRootFragment)
        vpEpisodes.adapter = pagerAdapter

        TabLayoutMediator(tlEpisodes, vpEpisodes) { tab, position ->
            tab.text = getString(pagerAdapter.getTitle(position))
        }.attach()
    }
}