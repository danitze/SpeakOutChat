package com.coursework.speakoutchat.auth_domain_data.use_case

import com.coursework.speakoutchat.auth_domain_data.data.UserCredentials
import com.coursework.speakoutchat.auth_domain_data.repository.AuthRepository
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend fun signUp(name: String, password: String): Result<Unit> {
        val userCredentials = UserCredentials(name, password)
        return withContext(Dispatchers.IO + CoroutineName("Sign up")) {
            authRepository.signUp(userCredentials)
        }
    }

}