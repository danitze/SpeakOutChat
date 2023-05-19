package com.coursework.speakoutchat.auth_ui.login

data class LoginUiState(
    val loginSuccessEvent: Unit? = null,
    val loginErrorEvent: LoginErrorEvent? = null
)

class LoginErrorEvent(val messageId: Int)
