package com.akerimtay.rickandmorty.core.presentation.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.akerimtay.rickandmorty.core.presentation.R
import com.akerimtay.rickandmorty.core.presentation.databinding.ViewSearchLabelBinding
import com.akerimtay.rickandmorty.core.presentation.util.extensions.setOnSafeClickListener

class SearchLabelView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding = ViewSearchLabelBinding.inflate(LayoutInflater.from(context), this, true)

    var text: String
        get() = binding.textView.text.toString()
        set(value) {
            binding.textView.text = value
        }

    var showFilter: Boolean
        get() = binding.filterGroup.isVisible
        set(value) {
            binding.filterGroup.isVisible = value
        }

    var onSearchClickListener: ((Unit) -> Unit)? = null
    var onFilterClickListener: ((Unit) -> Unit)? = null
    var onViewClickListener: ((Unit) -> Unit)? = null

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.SearchLabelView, defStyleAttr, defStyleRes)
        try {
            with(binding) {
                textView.text = attributes.getString(R.styleable.SearchLabelView_text).orEmpty()
                filterGroup.isVisible = attributes.getBoolean(R.styleable.SearchLabelView_showFilter, false)
                searchImageView.setOnSafeClickListener { onSearchClickListener?.invoke(Unit) }
                filterImageView.setOnSafeClickListener { onFilterClickListener?.invoke(Unit) }
                root.setOnSafeClickListener { onViewClickListener?.invoke(Unit) }
            }
        } finally {
            attributes.recycle()
        }
    }
}