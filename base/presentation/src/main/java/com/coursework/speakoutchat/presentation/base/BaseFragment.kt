package com.coursework.speakoutchat.presentation.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupUiComponents()
        setupUiListeners()
    }

    abstract fun setupObservers()

    abstract fun setupUiComponents()

    abstract fun setupUiListeners()

    protected inline fun launchWhenStarted(
        coroutineName: String,
        crossinline block: suspend (CoroutineScope) -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launch(CoroutineName(coroutineName)) {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                block.invoke(this)
            }
        }
    }

}