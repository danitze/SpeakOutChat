package com.coursework.speakoutchat.chat_ui

import kotlinx.coroutines.flow.StateFlow

interface NavigateToMenuEventApi {

    val navigateToMenuEvent: StateFlow<Unit?>

    fun navigateToMenu()

    fun navigateToMenuEventConsumed()

}