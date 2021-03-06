package com.akerimtay.rickandmorty.characters.presentation.list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.paging.CombinedLoadStates
import com.akerimtay.rickandmorty.characters.R
import com.akerimtay.rickandmorty.characters.databinding.FragmentCharactersBinding
import com.akerimtay.rickandmorty.characters.presentation.ComponentViewModel
import com.akerimtay.rickandmorty.characters.presentation.list.adapter.CharacterAdapter
import com.akerimtay.rickandmorty.core.presentation.LoadStateAdapter
import com.akerimtay.rickandmorty.core.presentation.base.BaseFragment
import com.akerimtay.rickandmorty.core.presentation.util.DefaultItemDecorator
import com.akerimtay.rickandmorty.core.presentation.util.extensions.launchWhenStarted
import com.akerimtay.rickandmorty.core.presentation.util.extensions.px
import com.akerimtay.rickandmorty.core.presentation.viewbinding.viewBinding
import javax.inject.Inject
import kotlinx.coroutines.flow.onEach

class CharactersFragment : BaseFragment(R.layout.fragment_characters) {

    @Inject
    internal lateinit var viewModelFactory: CharactersViewModelFactory

    private val viewBinding by viewBinding(FragmentCharactersBinding::bind)
    private val viewModel: CharactersViewModel by viewModels { viewModelFactory }
    private val componentViewModel: ComponentViewModel by viewModels()
    private val characterAdapter: CharacterAdapter by lazy { CharacterAdapter() }

    private val loadStateListener = { states: CombinedLoadStates -> viewModel.onLoadStates(states) }

    override fun onAttach(context: Context) {
        componentViewModel.component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(viewBinding) {
        super.onViewCreated(view, savedInstanceState)
        srlCharacters.setOnRefreshListener { characterAdapter.refresh() }

        val loadStateAdapter = LoadStateAdapter(characterAdapter::retry)
        characterAdapter.addLoadStateListener(loadStateListener)
        characterAdapter.withLoadStateFooter(loadStateAdapter)
        rvCharacters.adapter = characterAdapter
        rvCharacters.addItemDecoration(DefaultItemDecorator(divider = DIVIDER_SIZE.px))

        viewModel.actions
            .onEach { action ->

            }
            .launchWhenStarted(viewLifecycleOwner)
        viewModel.isSwipeRefreshing.launchWhenStarted(viewLifecycleOwner) { srlCharacters.isRefreshing = it }
        viewModel.charactersCount.launchWhenStarted(viewLifecycleOwner) { count ->
            tvCharacterCount.text = getString(R.string.characters_count_format, count)
        }
        viewModel.items.launchWhenStarted(viewLifecycleOwner) { characterAdapter.submitData(it) }
    }

    companion object {

        private const val DIVIDER_SIZE = 24
    }
}