package com.coursework.speakoutchat.auth_domain_data.use_case

import com.coursework.speakoutchat.auth_domain_data.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserInfoUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    fun observeUserName(): Flow<String> = authRepository.userInfoFlow
        .filterNotNull()
        .map { it.userId }

    fun observeToken(): Flow<String?> = authRepository.userInfoFlow.map { it?.token }

}