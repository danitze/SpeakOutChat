package com.coursework.speakoutchat.repository_in_memory.impl

import com.coursework.speakoutchat.menu_ui.menu.NavigateToLoginEventApi
import com.coursework.speakoutchat.repository_in_memory.EventRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class NavigateToLoginEventImpl @Inject constructor(
    private val eventRepository: EventRepository
) : NavigateToLoginEventApi {

    override val navigateToLoginEvent: StateFlow<Unit?> =
        eventRepository.navigateToLoginEvent.asStateFlow()

    override fun navigateToLogin() {
        eventRepository.navigateToLoginEvent.value = Unit
    }

    override fun navigateToLoginEventConsumed() {
        eventRepository.navigateToLoginEvent.value = null
    }

}