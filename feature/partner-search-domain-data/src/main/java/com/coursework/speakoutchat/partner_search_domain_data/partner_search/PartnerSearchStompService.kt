package com.coursework.speakoutchat.partner_search_domain_data.partner_search

import com.coursework.speakoutchat.auth_domain_data.data.UserInfo
import com.coursework.speakoutchat.common.extension.asToken
import com.coursework.speakoutchat.common.extension.toFlow
import com.coursework.speakoutchat.network.type.PairingStompClient
import kotlinx.coroutines.flow.Flow
import ua.naiksoftware.stomp.StompClient
import ua.naiksoftware.stomp.dto.LifecycleEvent
import ua.naiksoftware.stomp.dto.StompHeader
import ua.naiksoftware.stomp.dto.StompMessage
import javax.inject.Inject

class PartnerSearchStompService @Inject constructor(
    @PairingStompClient private val stompClient: StompClient
) {

    fun observeStompLifecycle(): Flow<LifecycleEvent> = stompClient.lifecycle().toFlow()

    fun observeMessages(userId: String): Flow<StompMessage> = stompClient
        .topic("/queue/pair/$userId")
        .toFlow()

    fun connect(userInfo: UserInfo) {
        val headers = listOf(StompHeader("Authorization", userInfo.token.asToken()))
        stompClient.connect(headers)
    }

    fun disconnect() {
        stompClient.disconnect()
    }

    fun sendPairingMessage(userTopicSerialized: String) {
        stompClient.send("/queue/pair", userTopicSerialized).subscribe()
    }

}