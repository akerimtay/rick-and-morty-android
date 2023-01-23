package com.akerimtay.rickandmorty.characters.presentation.ui.list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.paging.CombinedLoadStates
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.akerimtay.rickandmorty.characters.R
import com.akerimtay.rickandmorty.characters.databinding.FragmentCharactersBinding
import com.akerimtay.rickandmorty.characters.presentation.model.ListViewType
import com.akerimtay.rickandmorty.characters.presentation.ui.ComponentViewModel
import com.akerimtay.rickandmorty.characters.presentation.ui.list.adapter.CharacterAdapter
import com.akerimtay.rickandmorty.core.presentation.LoadStateAdapter
import com.akerimtay.rickandmorty.core.presentation.base.BaseFragment
import com.akerimtay.rickandmorty.core.presentation.util.DefaultItemDecorator
import com.akerimtay.rickandmorty.core.presentation.util.extensions.launchWhenStarted
import com.akerimtay.rickandmorty.core.presentation.util.extensions.px
import com.akerimtay.rickandmorty.core.presentation.util.extensions.setOnSafeClickListener
import com.akerimtay.rickandmorty.core.presentation.viewbinding.viewBinding
import javax.inject.Inject

class CharactersFragment : BaseFragment(R.layout.fragment_characters) {

    @Inject
    internal lateinit var viewModelFactory: CharactersViewModelFactory

    private val viewBinding by viewBinding(FragmentCharactersBinding::bind)
    private val viewModel: CharactersViewModel by viewModels { viewModelFactory }
    private val componentViewModel: ComponentViewModel by viewModels()

    private val loadStateListener = { states: CombinedLoadStates -> viewModel.onLoadStates(states) }

    override fun onAttach(context: Context) {
        componentViewModel.component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(viewBinding) {
        super.onViewCreated(view, savedInstanceState)
        val characterAdapter = CharacterAdapter().apply {
            val loadStateAdapter = LoadStateAdapter(::retry)
            addLoadStateListener(loadStateListener)
            withLoadStateFooter(loadStateAdapter)
        }
        rvCharacters.adapter = characterAdapter
        rvCharacters.addItemDecoration(DefaultItemDecorator(divider = DIVIDER_SIZE.px))

        srlCharacters.setOnRefreshListener { characterAdapter.refresh() }
        ivSelectorViewType.setOnSafeClickListener { viewModel.onChangeViewTypeClicked() }

        viewModel.isSwipeRefreshing.launchWhenStarted(viewLifecycleOwner) { srlCharacters.isRefreshing = it }
        viewModel.selectedListViewType.launchWhenStarted(viewLifecycleOwner) {
            rvCharacters.layoutManager = when (it) {
                ListViewType.HORIZONTAL -> LinearLayoutManager(requireContext())
                ListViewType.GRID -> GridLayoutManager(requireContext(), SPAN_COUNT)
            }
        }
        viewModel.listViewTypeIcon.launchWhenStarted(viewLifecycleOwner) {
            ivSelectorViewType.setImageResource(it)
        }
        viewModel.charactersCount.launchWhenStarted(viewLifecycleOwner) { count ->
            tvCharacterCount.text = getString(R.string.characters_count_format, count)
        }
        viewModel.items.launchWhenStarted(viewLifecycleOwner) {
            characterAdapter.submitData(it)
        }
    }

    companion object {

        private const val DIVIDER_SIZE = 24
        private const val SPAN_COUNT = 2
    }
}