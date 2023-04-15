package com.coursework.speakoutchat.menu_ui

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.coursework.speakoutchat.menu_ui.databinding.FragmentMenuBinding
import com.coursework.speakoutchat.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : BaseFragment(R.layout.fragment_menu) {

    private val binding by viewBinding(FragmentMenuBinding::bind)

    private val menuViewModel: MenuViewModel by viewModels()

    override fun setupObservers() {
        launchWhenStarted("Observe menuViewModel") {
            TODO("Not yet implemented")
        }
    }

    override fun setupUiComponents() {
        TODO("Not yet implemented")
    }

    override fun setupUiListeners() {
        TODO("Not yet implemented")
    }
}