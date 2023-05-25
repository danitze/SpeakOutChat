package com.coursework.speakoutchat.chat_domain_data.remote

import com.coursework.speakoutchat.auth_domain_data.data.UserInfo
import com.coursework.speakoutchat.common.extension.asToken
import com.coursework.speakoutchat.common.extension.require
import com.coursework.speakoutchat.common.extension.toFlow
import com.coursework.speakoutchat.network.type.ChatStompClient
import kotlinx.coroutines.flow.Flow
import ua.naiksoftware.stomp.StompClient
import ua.naiksoftware.stomp.dto.LifecycleEvent
import ua.naiksoftware.stomp.dto.StompHeader
import ua.naiksoftware.stomp.dto.StompMessage
import javax.inject.Inject

class ChatStompService @Inject constructor(
    @ChatStompClient private val stompClient: StompClient
) {

    fun observeStompLifecycle(): Flow<LifecycleEvent> = stompClient.lifecycle().toFlow()

    fun observeMessages(userId: String): Flow<StompMessage> = stompClient
        .topic("/queue/message/$userId")
        .toFlow()

    fun connect(userInfo: UserInfo) {
        val headers = listOf(
            StompHeader("Authorization", userInfo.token.asToken()),
            StompHeader("user_id", userInfo.userId)
        )
        stompClient.connect(headers)
    }

    fun disconnect() {
        stompClient.disconnect()
    }

    fun sendMessage(messageSerialized: String) {
        stompClient.send("/queue/message", messageSerialized).subscribe()
    }

}