package com.coursework.speakoutchat.chat_domain_data.use_case

import com.coursework.speakoutchat.auth_domain_data.provider.UserInfoProvider
import com.coursework.speakoutchat.chat_domain_data.data.MessageUiModel
import com.coursework.speakoutchat.chat_domain_data.mapper.toMessageUiModel
import com.coursework.speakoutchat.chat_domain_data.repository.ChatRepository
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MessagingUseCase @Inject constructor(
    private val repository: ChatRepository,
    userInfoProvider: UserInfoProvider
) {

    val savedMessagesFlow: Flow<List<MessageUiModel>> = combine(
        userInfoProvider.userInfoFlow.filterNotNull().map { it.userId },
        repository.observeSavedMessages()
    ) { userId, savedMessages ->
        savedMessages.map { toMessageUiModel(it, userId) }
    }

    val partnerDisconnectedMessagesFlow: Flow<Unit> = repository
        .observePartnerDisconnectedMessages()

    suspend fun startObservingMessages() =
        withContext(Dispatchers.IO + CoroutineName("Start observing messages")) {
            repository.observeMessages().launchIn(this)
        }

    suspend fun sendMessage(content: String, receiverId: String): Result<Unit> =
        withContext(Dispatchers.IO + CoroutineName("Send message")) {
            repository.sendMessage(content, receiverId)
        }

}