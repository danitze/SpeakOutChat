package com.coursework.speakoutchat.chat_domain_data.data

import java.util.Date

data class MessageUiModel(
    val id: Int,
    val messageType: MessageType,
    val content: String,
    val date: Date
)

enum class MessageType {
    MY_MESSAGE, PARTNER_MESSAGE
}
