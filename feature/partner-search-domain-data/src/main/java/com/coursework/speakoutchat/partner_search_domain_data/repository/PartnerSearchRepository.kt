package com.coursework.speakoutchat.partner_search_domain_data.repository

import com.coursework.speakoutchat.auth_domain_data.provider.UserInfoProvider
import com.coursework.speakoutchat.common.extension.flatMap
import com.coursework.speakoutchat.network.stomp.StompLifecycleEvent
import com.coursework.speakoutchat.partner_search_domain_data.partner_search.PartnerSearchNetSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PartnerSearchRepository @Inject constructor(
    private val partnerSearchNetSource: PartnerSearchNetSource,
    private val userInfoProvider: UserInfoProvider
) {

    fun observeStompLifecycle(): Flow<StompLifecycleEvent> = partnerSearchNetSource
        .observeStompLifecycle()

    fun observeMessages(): Flow<String> = userInfoProvider.userInfoFlow
        .filterNotNull()
        .map { it.userId }
        .flatMapLatest { partnerSearchNetSource.observeMessages(it) }

    suspend fun connect(): Result<Unit> = userInfoProvider.getUserInfo()
        .flatMap { userInfo ->
            partnerSearchNetSource.connect(userInfo)
        }

    fun  disconnect(): Result<Unit> = partnerSearchNetSource.disconnect()

    suspend fun sendPairingMessage(topicId: String): Result<Unit> = userInfoProvider.getUserInfo()
        .map { userInfo -> userInfo.userId }
        .flatMap { userId ->
            partnerSearchNetSource.sendPairingMessage(userId, topicId)
        }

}