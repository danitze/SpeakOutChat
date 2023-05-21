package com.coursework.speakoutchat.partner_search_domain_data.use_case

import com.coursework.speakoutchat.partner_search_domain_data.data.StompLifecycleEvent
import com.coursework.speakoutchat.partner_search_domain_data.repository.PartnerSearchRepository
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ConnectUseCase @Inject constructor(
    private val repository: PartnerSearchRepository
) {

    fun observeStompLifecycle(): Flow<StompLifecycleEvent> = repository
        .observeStompLifecycle()

    suspend fun connect(): Result<Unit> =
        withContext(Dispatchers.IO + CoroutineName("Connect")) {
            repository.connect()
        }

}