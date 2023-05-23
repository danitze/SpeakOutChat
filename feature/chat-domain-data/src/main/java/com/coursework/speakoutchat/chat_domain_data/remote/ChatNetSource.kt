package com.coursework.speakoutchat.chat_domain_data.remote

import com.coursework.speakoutchat.auth_domain_data.data.UserInfo
import com.coursework.speakoutchat.chat_domain_data.data.Message
import com.coursework.speakoutchat.chat_domain_data.mapper.toJson
import com.coursework.speakoutchat.chat_domain_data.mapper.toMessage
import com.coursework.speakoutchat.network.stomp.StompLifecycleEvent
import com.coursework.speakoutchat.network.stomp.mapToStompLifecycleEvent
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ChatNetSource @Inject constructor(
    private val chatStompService: ChatStompService,
    private val moshi: Moshi
) {

    fun observeStompLifecycle(): Flow<StompLifecycleEvent> = chatStompService
        .observeStompLifecycle().map { mapToStompLifecycleEvent(it) }

    fun observeMessages(userId: String): Flow<Message> = chatStompService
        .observeMessages(userId)
        .map { it.payload.removeSurrounding("\"") }
        .map { toMessage(moshi, it) }.filterNotNull()

    fun connect(userInfo: UserInfo): Result<Unit> = kotlin.runCatching {
        chatStompService.connect(userInfo)
    }

    fun sendMessage(userId: String, receiverId: String, content: String): Result<Unit> = kotlin
        .runCatching {
            val message = Message(
                senderId = userId,
                receiverId = receiverId,
                content = content,
                timeStamp = System.currentTimeMillis()
            )
            val messageSerialized = toJson(moshi, message)
            chatStompService.sendMessage(messageSerialized)
        }

}