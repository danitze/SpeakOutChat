package com.coursework.speakoutchat.auth_domain_data.use_case

import com.coursework.speakoutchat.auth_domain_data.data.AuthRequest
import com.coursework.speakoutchat.auth_domain_data.repository.AuthRepository
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend fun login(name: String, password: String): Result<Unit> {
        val authRequest = AuthRequest(name, password)
        return withContext(Dispatchers.IO + CoroutineName("Login")) {
            authRepository.login(authRequest)
        }
    }

}