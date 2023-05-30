package com.coursework.speakoutchat.auth_domain_data.login

import com.coursework.speakoutchat.auth_domain_data.data.AuthRequest
import javax.inject.Inject

class LoginNetSource @Inject constructor(
    private val loginApiService: LoginApiService
) {

    suspend fun authenticate(authRequest: AuthRequest): Result<String> = kotlin.runCatching {
        loginApiService.authenticate(authRequest).body()?.string()
            ?: throw IllegalArgumentException("Unauthorized")
    }

}