package com.akerimtay.rickandmorty.characters.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akerimtay.rickandmorty.characters.R
import com.akerimtay.rickandmorty.characters.databinding.FragmentCharactersBinding
import com.akerimtay.rickandmorty.core.presentation.base.BaseFragment
import com.akerimtay.rickandmorty.core.presentation.util.extensions.launchWhenStarted
import com.akerimtay.rickandmorty.core.presentation.viewbinding.viewBinding
import javax.inject.Inject

class CharactersFragment : BaseFragment(R.layout.fragment_characters) {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<CharactersViewModel>

    private val viewBinding by viewBinding(FragmentCharactersBinding::bind)
    private val viewModel: CharactersViewModel by viewModels { viewModelFactory }
    private val componentViewModel: ComponentViewModel by viewModels()

    override fun onAttach(context: Context) {
        componentViewModel.component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(viewBinding) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.charactersCount.launchWhenStarted(viewLifecycleOwner) { count ->
            tvCharacterCount.text = getString(R.string.characters_count_format, count)
        }
    }
}

// TODO("Move to common module")
class ViewModelFactory<VM : ViewModel> @Inject constructor(
    private val viewModel: VM
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModel as T
}