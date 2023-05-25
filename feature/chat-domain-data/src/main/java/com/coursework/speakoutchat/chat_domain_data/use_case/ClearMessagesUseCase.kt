package com.coursework.speakoutchat.chat_domain_data.use_case

import com.coursework.speakoutchat.chat_domain_data.repository.ChatRepository
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ClearMessagesUseCase @Inject constructor(
    private val repository: ChatRepository
) {

    suspend fun clearMessages(): Result<Unit> =
        withContext(Dispatchers.IO + CoroutineName("Clear messages")) {
            repository.clearMessages()
        }

}