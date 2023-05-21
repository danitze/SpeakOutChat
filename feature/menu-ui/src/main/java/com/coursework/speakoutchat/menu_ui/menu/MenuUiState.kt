package com.coursework.speakoutchat.menu_ui.menu

data class MenuUiState(
    val userName: String = "",
    val logoutSuccessEvent: Unit? = null,
    val logoutFailureEvent: LogoutFailureEvent? = null
)

class LogoutFailureEvent(val messageId: Int)
