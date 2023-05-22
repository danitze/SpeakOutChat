package com.coursework.speakoutchat.chat_domain_data.mapper

import com.coursework.speakoutchat.chat_domain_data.data.Message
import com.coursework.speakoutchat.chat_domain_data.data.MessageType
import com.coursework.speakoutchat.chat_domain_data.data.MessageUiModel
import java.util.Date

fun toMessageUiModel(message: Message, userId: String): MessageUiModel = with(message) {
    MessageUiModel(
        id = id,
        messageType = if (userId == senderId) MessageType.MY_MESSAGE else MessageType.PARTNER_MESSAGE,
        content = content,
        date = Date(timeStamp)
    )
}