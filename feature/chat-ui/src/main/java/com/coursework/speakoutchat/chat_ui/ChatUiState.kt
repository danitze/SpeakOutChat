package com.coursework.speakoutchat.chat_ui

import com.coursework.speakoutchat.chat_domain_data.data.MessageUiModel

data class ChatUiState(
    val currentMessage: String = "",
    val messages: List<MessageUiModel> = listOf()
) {

    val isSendButtonEnabled: Boolean
        get() = currentMessage.isNotBlank()

}
