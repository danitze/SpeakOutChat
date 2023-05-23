package com.coursework.speakoutchat.chat_domain_data.mapper

import com.coursework.speakoutchat.chat_domain_data.data.Message
import com.coursework.speakoutchat.chat_domain_data.data.MessageType
import com.coursework.speakoutchat.chat_domain_data.data.MessageUiModel
import com.squareup.moshi.Moshi
import java.util.Date

fun toMessageUiModel(message: Message, userId: String): MessageUiModel = with(message) {
    MessageUiModel(
        id = id,
        messageType = if (userId == senderId) MessageType.MY_MESSAGE else MessageType.PARTNER_MESSAGE,
        content = content,
        date = Date(timeStamp)
    )
}

fun toMessage(moshi: Moshi, json: String): Message? = moshi.adapter(Message::class.java)
    .fromJson(json)

fun toJson(moshi: Moshi, message: Message): String = moshi.adapter(Message::class.java)
    .toJson(message)