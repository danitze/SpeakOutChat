package com.coursework.speakoutchat.chat_ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coursework.speakoutchat.chat_domain_data.data.MessageUiModel
import com.coursework.speakoutchat.chat_domain_data.use_case.ClearMessagesUseCase
import com.coursework.speakoutchat.chat_domain_data.use_case.ConnectUseCase
import com.coursework.speakoutchat.chat_domain_data.use_case.MessagingUseCase
import com.coursework.speakoutchat.common.extension.require
import com.coursework.speakoutchat.network.stomp.StompLifecycleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val connectUseCase: ConnectUseCase,
    private val messagingUseCase: MessagingUseCase,
    private val clearMessagesUseCase: ClearMessagesUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState = _uiState.asStateFlow()

    private val stompLifecycleFlow = connectUseCase.observeStompLifecycle().onEach { event ->
        when (event) {
            is StompLifecycleEvent.Opened -> onStompConnected()
            is StompLifecycleEvent.Error -> onStompConnectionError()
            else -> {
                // TODO: Implement later
            }
        }
    }

    private val messagesFlow: Flow<List<MessageUiModel>> = messagingUseCase
        .savedMessagesFlow
        .onEach { messages ->
            _uiState.update { it.copy(messages = messages) }
        }

    private val partnerDisconnectedEventFlow: Flow<Unit> = messagingUseCase
        .partnerDisconnectedMessagesFlow
        .onEach {
            _uiState.update { it.copy(partnerDisconnectedEvent = Unit) }
        }

    init {
        viewModelScope.launch {
            clearMessagesUseCase.clearMessages()
            observeIntermediateFlows()
            connect()
        }
    }

    fun onMessageChanged(message: String) {
        _uiState.update { it.copy(currentMessage = message) }
    }

    fun sendMessage() {
        viewModelScope.launch {
            val content = uiState.value.currentMessage
            val receiverId = savedStateHandle.get<String>("partner_id").require()
            messagingUseCase.sendMessage(content, receiverId)
            _uiState.update { it.copy(clearMessageEditTextEvent = Unit) }
        }
    }

    fun disconnect() {
        viewModelScope.launch {
            connectUseCase.disconnect()
            _uiState.update { it.copy(disconnectedEvent = Unit) }
        }
    }

    private fun connect() {
        viewModelScope.launch {
            connectUseCase.connect()
        }
    }

    private fun onStompConnected() {
        viewModelScope.launch {
            messagingUseCase.startObservingMessages()
        }
    }

    private fun onStompConnectionError() {
        _uiState.update { it.copy(stompErrorEvent = Unit) }
    }

    private fun observeIntermediateFlows() {
        viewModelScope.launch {
            stompLifecycleFlow.launchIn(this)
            messagesFlow.launchIn(this)
            partnerDisconnectedEventFlow.launchIn(this)
        }
    }

    fun stompErrorEventConsumed() {
        _uiState.update { it.copy(stompErrorEvent = null) }
    }

    fun clearMessageEditTextEventConsumed() {
        _uiState.update { it.copy(clearMessageEditTextEvent = null) }
    }

    fun disconnectedEventConsumed() {
        _uiState.update { it.copy(disconnectedEvent = null) }
    }

    fun partnerDisconnectedEventConsumed() {
        _uiState.update { it.copy(partnerDisconnectedEvent = null) }
    }

}