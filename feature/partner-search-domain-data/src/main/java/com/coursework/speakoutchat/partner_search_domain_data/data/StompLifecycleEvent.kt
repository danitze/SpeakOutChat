package com.coursework.speakoutchat.partner_search_domain_data.data

import ua.naiksoftware.stomp.dto.LifecycleEvent

sealed class StompLifecycleEvent {

    object Opened : StompLifecycleEvent()

    object Closed : StompLifecycleEvent()

    object FailedServerHeartBeat : StompLifecycleEvent()

    data class Error(val message: String) : StompLifecycleEvent()

}

fun mapToStompLifecycleEvent(event: LifecycleEvent): StompLifecycleEvent = when(event.type) {
    LifecycleEvent.Type.OPENED -> StompLifecycleEvent.Opened
    LifecycleEvent.Type.CLOSED -> StompLifecycleEvent.Closed
    LifecycleEvent.Type.FAILED_SERVER_HEARTBEAT -> StompLifecycleEvent.FailedServerHeartBeat
    LifecycleEvent.Type.ERROR -> StompLifecycleEvent.Error(event.message ?: "")
}
