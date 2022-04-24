package com.akerimtay.rickandmorty.character.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.akerimtay.rickandmorty.R
import com.akerimtay.rickandmorty.common.base.BaseFragment
import com.akerimtay.rickandmorty.common.util.showToast
import com.akerimtay.rickandmorty.common.viewbinding.viewBinding
import com.akerimtay.rickandmorty.databinding.FragmentCharactersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : BaseFragment(R.layout.fragment_characters) {

    private val binding by viewBinding(FragmentCharactersBinding::bind)
    private val viewModel by viewModels<CharactersViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            searchLabelView.onViewClickListener = { showToast("view click") }
            searchLabelView.onSearchClickListener = { showToast("search click") }
            searchLabelView.onFilterClickListener = { showToast("filter click") }
        }

        viewModel.actions.observe(viewLifecycleOwner) { action ->
            when (action) {
                is CharactersAction.ShowToast -> showToast(message = action.message)
            }
        }
        viewModel.charactersCount.observe(viewLifecycleOwner) { count ->
            binding.characterCountTextView.text = getString(R.string.characters_count_format, count)
        }
    }
}