package com.coursework.speakoutchat.auth_domain_data.sign_up

import com.coursework.speakoutchat.auth_domain_data.data.UserCredentials
import javax.inject.Inject

class SignUpNetSource @Inject constructor(
    private val signUpApiService: SignUpApiService
) {

    suspend fun signUp(userCredentials: UserCredentials): Result<Unit> = kotlin.runCatching {
        signUpApiService.signUp(userCredentials)
    }

}