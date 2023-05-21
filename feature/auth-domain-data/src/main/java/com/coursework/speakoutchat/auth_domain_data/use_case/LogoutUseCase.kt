package com.coursework.speakoutchat.auth_domain_data.use_case

import com.coursework.speakoutchat.auth_domain_data.repository.AuthRepository
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend fun logout() = withContext(Dispatchers.IO + CoroutineName("Logout")) {
        authRepository.deleteUserInfo()
    }

}