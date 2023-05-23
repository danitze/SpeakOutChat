package com.coursework.speakoutchat.chat_domain_data.local

import com.coursework.speakoutchat.chat_domain_data.data.Message
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MessageLocalSource @Inject constructor(
    private val messageDao: MessageDao
) {

    fun observeMessages(): Flow<List<Message>> = messageDao.observeMessages()

    suspend fun clearMessages(): Result<Unit> = kotlin.runCatching {
        messageDao.clearMessages()
    }

    suspend fun upsertMessage(
        senderId: String,
        receiverId: String,
        content: String
    ): Result<Unit> = kotlin.runCatching {
        val message = Message(
            senderId = senderId,
            receiverId = receiverId,
            content = content,
            timeStamp = System.currentTimeMillis()
        )
        upsertMessage(message)
    }

    suspend fun upsertMessage(message: Message): Result<Unit> = kotlin.runCatching {
        messageDao.upsertMessage(message)
    }

}