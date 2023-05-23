package com.coursework.speakoutchat.chat_domain_data.repository

import com.coursework.speakoutchat.auth_domain_data.provider.UserInfoProvider
import com.coursework.speakoutchat.chat_domain_data.data.Message
import com.coursework.speakoutchat.chat_domain_data.local.MessageLocalSource
import com.coursework.speakoutchat.chat_domain_data.remote.ChatNetSource
import com.coursework.speakoutchat.common.extension.flatMap
import com.coursework.speakoutchat.network.stomp.StompLifecycleEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ChatRepository @Inject constructor(
    private val chatNetSource: ChatNetSource,
    private val messageLocalSource: MessageLocalSource,
    private val userInfoProvider: UserInfoProvider
) {

    fun observeStompLifecycle(): Flow<StompLifecycleEvent> = chatNetSource
        .observeStompLifecycle()

    fun observeMessages(): Flow<Message> = userInfoProvider.userInfoFlow
        .filterNotNull()
        .map { it.userId }
        .flatMapLatest { chatNetSource.observeMessages(it) }
        .onEach { message ->
            messageLocalSource.upsertMessage(message)
        }

    fun observeSavedMessages(): Flow<List<Message>> = messageLocalSource.observeMessages()

    suspend fun connect(): Result<Unit> = userInfoProvider.getUserInfo()
        .flatMap { userInfo ->
            chatNetSource.connect(userInfo)
        }

    suspend fun sendMessage(content: String, receiverId: String): Result<Unit> = userInfoProvider
        .getUserInfo()
        .flatMap { userInfo ->
            chatNetSource.sendMessage(
                userId = userInfo.userId,
                receiverId = receiverId,
                content = content
            )
        }

}