package com.coursework.speakoutchat.chat_ui

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.coursework.speakoutchat.chat_ui.databinding.FragmentChatBinding
import com.coursework.speakoutchat.presentation.base.BaseFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ChatFragment : BaseFragment(R.layout.fragment_chat) {

    private val binding by viewBinding(FragmentChatBinding::bind)

    private val args: ChatFragmentArgs by navArgs()

    private val viewModel: ChatViewModel by viewModels()

    override fun setupObservers() {
        launchWhenStarted("Observe ChatFragment data") { scope ->
            viewModel.uiState.onEach { uiState ->

                binding.buttonSend.isEnabled = uiState.isSendButtonEnabled

            }.launchIn(scope)
        }
    }

    override fun setupUiComponents() {
        with(binding) {
            toolbar.setTextDescription(getString(args.topicTitle))
        }
    }

    override fun setupUiListeners() {
    }
}