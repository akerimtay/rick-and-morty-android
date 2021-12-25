package com.akerimtay.rickandmorty

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.akerimtay.rickandmorty.common.util.showToast
import com.akerimtay.rickandmorty.common.viewbinding.viewBinding
import com.akerimtay.rickandmorty.databinding.FragmentCharactersBinding

class CharactersFragment : Fragment(R.layout.fragment_characters) {
    private val binding by viewBinding(FragmentCharactersBinding::bind)

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
    }
}