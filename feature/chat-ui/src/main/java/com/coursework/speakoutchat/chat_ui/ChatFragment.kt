package com.coursework.speakoutchat.chat_ui

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.coursework.speakoutchat.chat_ui.adapter.ChatAdapter
import com.coursework.speakoutchat.chat_ui.databinding.FragmentChatBinding
import com.coursework.speakoutchat.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class ChatFragment : BaseFragment(R.layout.fragment_chat) {

    private val binding by viewBinding(FragmentChatBinding::bind)

    private val args: ChatFragmentArgs by navArgs()

    private val viewModel: ChatViewModel by viewModels()

    @Inject
    lateinit var chatAdapter: ChatAdapter

    override fun setupObservers() {
        launchWhenStarted("Observe ChatFragment data") { scope ->
            viewModel.uiState.onEach { uiState ->

                binding.buttonSend.isEnabled = uiState.isSendButtonEnabled

                if (uiState.clearMessageEditTextEvent != null) {
                    binding.editTextMessage.setText("")
                    viewModel.clearMessageEditTextEventConsumed()
                }

                chatAdapter.submitList(uiState.messages)

            }.launchIn(scope)
        }
    }

    override fun setupUiComponents() {
        with(binding) {
            toolbar.setTextDescription(getString(args.topicTitle))
            setupRecyclerView()
        }
    }

    override fun setupUiListeners() {
        with(binding) {
            buttonSend.setOnClickListener {
                hideKeyboard()
                viewModel.sendMessage()
            }

            editTextMessage.doOnTextChanged { text, _, _, _ ->
                viewModel.onMessageChanged(text?.toString() ?: "")
            }
        }
    }

    override fun onDestroyView() {
        binding.recyclerChat.adapter = null
        super.onDestroyView()
    }

    private fun setupRecyclerView() {
        with(binding.recyclerChat) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = chatAdapter
        }
    }

    private fun hideKeyboard() {
        (requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow((activity?.currentFocus ?: View(context)).windowToken, 0)
    }
}