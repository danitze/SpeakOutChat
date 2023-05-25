package com.coursework.speakoutchat.chat_ui

import com.coursework.speakoutchat.chat_domain_data.data.MessageUiModel

data class ChatUiState(
    val currentMessage: String = "",
    val messages: List<MessageUiModel> = listOf(),
    val stompErrorEvent: Unit? = null,
    val clearMessageEditTextEvent: Unit? = null,
    val disconnectedEvent: Unit? = null
) {

    val isSendButtonEnabled: Boolean
        get() = currentMessage.isNotBlank()

}
