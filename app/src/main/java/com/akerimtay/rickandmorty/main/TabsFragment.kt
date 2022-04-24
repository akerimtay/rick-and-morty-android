package com.akerimtay.rickandmorty.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.akerimtay.rickandmorty.R
import com.akerimtay.rickandmorty.core.presentation.viewbinding.viewBinding
import com.akerimtay.rickandmorty.databinding.FragmentTabsBinding

class TabsFragment : Fragment(R.layout.fragment_tabs) {
    private val binding by viewBinding(FragmentTabsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHost = childFragmentManager.findFragmentById(R.id.tabsContainer) as NavHostFragment
        val navController = navHost.navController
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}