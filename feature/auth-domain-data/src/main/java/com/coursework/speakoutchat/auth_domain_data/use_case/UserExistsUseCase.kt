package com.coursework.speakoutchat.auth_domain_data.use_case

import com.coursework.speakoutchat.auth_domain_data.repository.AuthRepository
import com.coursework.speakoutchat.common.extension.getOrDefault
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserExistsUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend fun checkUserExists(): Boolean =
        withContext(Dispatchers.IO + CoroutineName("Check user exists")) {
            authRepository.checkUserInfoExists().getOrDefault(false)
        }

}