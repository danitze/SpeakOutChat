package com.coursework.speakoutchat.menu_ui.menu

import kotlinx.coroutines.flow.StateFlow

interface NavigateToLoginEventApi {

    val navigateToLoginEvent: StateFlow<Unit?>

    fun navigateToLogin()

    fun navigateToLoginEventConsumed()

}