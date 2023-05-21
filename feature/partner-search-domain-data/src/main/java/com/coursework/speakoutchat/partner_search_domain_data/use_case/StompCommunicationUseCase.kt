package com.coursework.speakoutchat.partner_search_domain_data.use_case

import com.coursework.speakoutchat.partner_search_domain_data.repository.PartnerSearchRepository
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StompCommunicationUseCase @Inject constructor(
    private val repository: PartnerSearchRepository
) {

    fun observeMessages(): Flow<String> = repository.observeMessages()

    suspend fun sendPairingMessage(topicId: String): Result<Unit> =
        withContext(Dispatchers.IO + CoroutineName("Send message")) {
            repository.sendPairingMessage(topicId)
        }

}