package com.akerimtay.rickandmorty.episode.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.akerimtay.rickandmorty.core.presentation.base.BaseFragment
import com.akerimtay.rickandmorty.core.presentation.util.extensions.launchWhenStarted
import com.akerimtay.rickandmorty.core.presentation.viewbinding.viewBinding
import com.akerimtay.rickandmorty.episode.R
import com.akerimtay.rickandmorty.episode.databinding.FragmentEpisodesBinding
import javax.inject.Inject
import timber.log.Timber

class EpisodesFragment : BaseFragment(R.layout.fragment_episodes) {

    @Inject
    internal lateinit var viewModelFactory: EpisodesViewModelFactory

    private val viewBinding by viewBinding(FragmentEpisodesBinding::bind)
    private val viewModel: EpisodesViewModel by viewModels { viewModelFactory }
    private val componentViewModel: ComponentViewModel by viewModels()

    override fun onAttach(context: Context) {
        componentViewModel.component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(viewBinding) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.episodes.launchWhenStarted(viewLifecycleOwner) { Timber.d("episodes: $it") }
    }
}