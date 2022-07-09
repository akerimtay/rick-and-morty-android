package com.akerimtay.rickandmorty.core.presentation

import androidx.core.view.isVisible
import androidx.paging.LoadState
import com.akerimtay.rickandmorty.core.presentation.base.BaseViewHolder
import com.akerimtay.rickandmorty.core.presentation.databinding.ItemLoadStateBinding
import com.akerimtay.rickandmorty.core.presentation.util.extensions.setOnSafeClickListener

class LoadStateViewHolder(
    private val viewBinding: ItemLoadStateBinding,
    private val retryCallback: () -> Unit,
) : BaseViewHolder<LoadState>(viewBinding.root) {

    override fun onBind(item: LoadState): Unit = with(viewBinding) {
        bRetry.setOnSafeClickListener { retryCallback() }
        bRetry.isVisible = item is LoadState.Error

        progressBar.isVisible = item is LoadState.Loading

        tvError.isVisible = !(item as? LoadState.Error)?.error?.message.isNullOrBlank()
        tvError.text = (item as? LoadState.Error)?.error?.message
    }
}