package com.akerimtay.rickandmorty.core.presentation

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.akerimtay.rickandmorty.core.presentation.databinding.ItemLoadStateBinding
import com.akerimtay.rickandmorty.core.presentation.util.extensions.layoutInflater

class LoadStateAdapter(
    private val retryCallback: () -> Unit,
) : LoadStateAdapter<LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            viewBinding = ItemLoadStateBinding.inflate(parent.layoutInflater, parent, false),
            retryCallback = retryCallback
        )
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.onBind(loadState)
    }
}