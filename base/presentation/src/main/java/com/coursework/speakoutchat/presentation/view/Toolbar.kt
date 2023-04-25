package com.coursework.speakoutchat.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import com.coursework.speakoutchat.presentation.R
import com.coursework.speakoutchat.presentation.databinding.ToolbarBinding

class Toolbar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding = ToolbarBinding
        .inflate(LayoutInflater.from(context), this, true)

    var onStartIconClickListener: (() -> Unit)? = null

    init {
        context.withStyledAttributes(attrs, R.styleable.Toolbar) {
            with(binding) {
                getDrawable(R.styleable.Toolbar_start_icon)?.let { iconDrawable ->
                    imageStartIcon.setImageDrawable(iconDrawable)
                    imageStartIcon.isVisible = true
                    imageStartIcon.setOnClickListener {
                        onStartIconClickListener?.invoke()
                    }
                }

                getString(R.styleable.Toolbar_text_description)?.let { description ->
                    setTextDescription(description)
                }
            }
        }
    }

    fun setTextDescription(description: String) {
        binding.textDescription.text = description
        binding.textDescription.isVisible = true
    }

}