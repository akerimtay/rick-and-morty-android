package com.akerimtay.rickandmorty.episode.presentation.tab

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.akerimtay.rickandmorty.core.presentation.base.BaseFragment
import com.akerimtay.rickandmorty.core.presentation.util.extensions.launchWhenStarted
import com.akerimtay.rickandmorty.core.presentation.viewbinding.viewBinding
import com.akerimtay.rickandmorty.core.utils.args
import com.akerimtay.rickandmorty.core.utils.withArgs
import com.akerimtay.rickandmorty.episode.R
import com.akerimtay.rickandmorty.episode.databinding.FragmentEpisodesBinding
import com.akerimtay.rickandmorty.episode.presentation.ComponentViewModel
import com.akerimtay.rickandmorty.episode.presentation.model.PageType
import javax.inject.Inject
import timber.log.Timber

internal class EpisodesFragment : BaseFragment(R.layout.fragment_episodes) {

    @Inject
    internal lateinit var viewModelFactory: EpisodesViewModelFactory.Factory

    private val pageType: PageType by args(PAGE_TYPE_EXTRA)
    private val viewBinding by viewBinding(FragmentEpisodesBinding::bind)
    private val viewModel: EpisodesViewModel by viewModels { viewModelFactory.create(pageType) }
    private val componentViewModel: ComponentViewModel by viewModels()

    override fun onAttach(context: Context) {
        componentViewModel.component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(viewBinding) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.episodes.launchWhenStarted(viewLifecycleOwner) {
            Timber.e("episodes: $it")
        }
    }

    companion object {

        private const val PAGE_TYPE_EXTRA = "PAGE_TYPE_EXTRA"

        fun create(pageType: PageType): EpisodesFragment {
            return EpisodesFragment()
                .withArgs(PAGE_TYPE_EXTRA to pageType)
        }
    }
}