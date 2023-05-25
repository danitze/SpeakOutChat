package com.coursework.speakoutchat.chat_domain_data.use_case

import com.coursework.speakoutchat.chat_domain_data.repository.ChatRepository
import com.coursework.speakoutchat.network.stomp.StompLifecycleEvent
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ConnectUseCase @Inject constructor(
    private val repository: ChatRepository
) {

    fun observeStompLifecycle(): Flow<StompLifecycleEvent> = repository
        .observeStompLifecycle()

    suspend fun connect(): Result<Unit> =
        withContext(Dispatchers.IO + CoroutineName("Connect")) {
            repository.connect()
        }

    suspend fun disconnect(): Result<Unit> =
        withContext(Dispatchers.IO + CoroutineName("Disconnect")) {
            repository.disconnect()
        }

}