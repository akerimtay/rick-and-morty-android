package com.akerimtay.rickandmorty

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.akerimtay.rickandmorty.common.viewbinding.viewBinding
import com.akerimtay.rickandmorty.databinding.FragmentCharactersBinding

class CharactersFragment : Fragment(R.layout.fragment_characters) {
    private val binding by viewBinding(FragmentCharactersBinding::bind)
    private var count: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.countCharactersTextView.text = count.toString()
        binding.increaseButton.setOnClickListener {
            count++
            binding.countCharactersTextView.text = count.toString()
        }
    }
}