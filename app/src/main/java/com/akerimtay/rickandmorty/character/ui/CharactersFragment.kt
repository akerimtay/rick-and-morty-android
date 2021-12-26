package com.akerimtay.rickandmorty.character.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.akerimtay.rickandmorty.R
import com.akerimtay.rickandmorty.common.base.BaseFragment
import com.akerimtay.rickandmorty.common.base.Resource
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
            searchLabelView.onViewClickListener = {
                showToast("view click")
            }
            searchLabelView.onSearchClickListener = {
                showToast("search click")
            }
            searchLabelView.onFilterClickListener = {
                showToast("filter click")
            }
        }
        viewModel.characters.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> showToast("loading")
                is Resource.Success -> showToast("success")
                is Resource.Error -> showToast("error")
            }
        }
    }
}