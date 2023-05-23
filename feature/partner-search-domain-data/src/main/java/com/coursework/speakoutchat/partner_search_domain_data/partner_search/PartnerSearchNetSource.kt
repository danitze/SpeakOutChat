package com.coursework.speakoutchat.partner_search_domain_data.partner_search

import com.coursework.speakoutchat.auth_domain_data.data.UserInfo
import com.coursework.speakoutchat.network.stomp.StompLifecycleEvent
import com.coursework.speakoutchat.partner_search_domain_data.data.UserTopic
import com.coursework.speakoutchat.network.stomp.mapToStompLifecycleEvent
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PartnerSearchNetSource @Inject constructor(
    private val partnerSearchStompService: PartnerSearchStompService,
    private val moshi: Moshi
) {

    fun observeStompLifecycle(): Flow<StompLifecycleEvent> = partnerSearchStompService
        .observeStompLifecycle().map { mapToStompLifecycleEvent(it) }

    fun observeMessages(userId: String): Flow<String> = partnerSearchStompService
        .observeMessages(userId).map { it.payload }

    fun connect(userInfo: UserInfo): Result<Unit> = kotlin.runCatching {
        partnerSearchStompService.connect(userInfo)
    }

    fun sendPairingMessage(userId: String, topicId: String): Result<Unit> = kotlin.runCatching {
        val userTopic = UserTopic(userId, topicId)
        val userTopicSerialized = moshi.adapter(UserTopic::class.java).toJson(userTopic)
        partnerSearchStompService.sendPairingMessage(userTopicSerialized)
    }

}