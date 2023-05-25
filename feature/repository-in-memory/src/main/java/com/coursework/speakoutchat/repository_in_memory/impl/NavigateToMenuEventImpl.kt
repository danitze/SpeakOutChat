package com.coursework.speakoutchat.repository_in_memory.impl

import com.coursework.speakoutchat.chat_ui.NavigateToMenuEventApi
import com.coursework.speakoutchat.repository_in_memory.EventRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class NavigateToMenuEventImpl @Inject constructor(
    private val eventRepository: EventRepository
) : NavigateToMenuEventApi {

    override val navigateToMenuEvent: StateFlow<Unit?> =
        eventRepository.navigateToMenuEvent.asStateFlow()

    override fun navigateToMenu() {
        eventRepository.navigateToMenuEvent.value = Unit
    }

    override fun navigateToMenuEventConsumed() {
        eventRepository.navigateToMenuEvent.value = null
    }

}