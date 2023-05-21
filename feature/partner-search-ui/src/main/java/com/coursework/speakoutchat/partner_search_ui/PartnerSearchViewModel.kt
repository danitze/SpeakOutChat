package com.coursework.speakoutchat.partner_search_ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coursework.speakoutchat.common.extension.require
import com.coursework.speakoutchat.partner_search_domain_data.data.StompLifecycleEvent
import com.coursework.speakoutchat.partner_search_domain_data.use_case.ConnectUseCase
import com.coursework.speakoutchat.partner_search_domain_data.use_case.StompCommunicationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PartnerSearchViewModel @Inject constructor(
    private val connectUseCase: ConnectUseCase,
    private val stompCommunicationUseCase: StompCommunicationUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(PartnerSearchUiState())
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

    private val partnerIdFlow = stompCommunicationUseCase.observeMessages().onEach { partnerId ->
        // TODO: move to chat
    }

    init {
        connect()
        observeIntermediateFlows()
    }

    private fun connect() {
        viewModelScope.launch {
            connectUseCase.connect()
        }
    }

    private fun onStompConnected() {
        stompCommunicationUseCase.observeMessages()
        sendPairingMessage()
    }

    private fun onStompConnectionError() {
        _uiState.update { it.copy(stompErrorEvent = Unit) }
    }

    private fun sendPairingMessage() {
        viewModelScope.launch {
            val topicId: String = savedStateHandle.get<String>("topic_id").require()
            stompCommunicationUseCase.sendPairingMessage(topicId)
        }
    }

    private fun observeIntermediateFlows() {
        viewModelScope.launch {
            stompLifecycleFlow.launchIn(this)
            partnerIdFlow.launchIn(this)
        }
    }

}