package com.coursework.speakoutchat.chat_domain_data.local

import com.coursework.speakoutchat.chat_domain_data.data.Message
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MessageLocalSource @Inject constructor(
    private val messageDao: MessageDao
) {

    fun observeMessages(): Flow<List<Message>> = messageDao.observeMessages()

    suspend fun upsertMessage(message: Message): Result<Unit> = kotlin.runCatching {
        messageDao.upsertMessage(message)
    }

}