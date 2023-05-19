package com.coursework.speakoutchat.auth_ui.sign_up

data class SignUpUiState(
    val signUpSuccessEvent: Unit? = null,
    val signUpErrorEvent: SignUpErrorEvent? = null
)

class SignUpErrorEvent(val messageId: Int)
