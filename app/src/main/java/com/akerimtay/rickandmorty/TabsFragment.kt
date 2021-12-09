package com.akerimtay.rickandmorty

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.akerimtay.rickandmorty.common.viewbinding.viewBinding
import com.akerimtay.rickandmorty.databinding.FragmentTabsBinding

class TabsFragment : Fragment(R.layout.fragment_tabs) {
    private val binding by viewBinding(FragmentTabsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(requireActivity(), R.id.tabsContainer)
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}