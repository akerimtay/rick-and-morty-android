package com.akerimtay.rickandmorty.episode.presentation.ui.root

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.akerimtay.rickandmorty.episode.presentation.model.PageType
import com.akerimtay.rickandmorty.episode.presentation.ui.tab.EpisodesFragment

internal class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int): Fragment {
        return EpisodesFragment.create(PageType.values()[position])
    }

    override fun getItemCount(): Int {
        return PageType.values().size
    }

    @StringRes
    fun getTitle(position: Int): Int {
        return PageType.values()[position].titleResId
    }
}