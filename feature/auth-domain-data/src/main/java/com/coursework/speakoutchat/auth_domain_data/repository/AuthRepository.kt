package com.coursework.speakoutchat.auth_domain_data.repository

import com.coursework.speakoutchat.auth_domain_data.data.AuthRequest
import com.coursework.speakoutchat.auth_domain_data.data.UserCredentials
import com.coursework.speakoutchat.auth_domain_data.data.UserInfo
import com.coursework.speakoutchat.auth_domain_data.login.LoginNetSource
import com.coursework.speakoutchat.auth_domain_data.mapper.mapToUserInfo
import com.coursework.speakoutchat.auth_domain_data.provider.UserInfoProvider
import com.coursework.speakoutchat.auth_domain_data.sign_up.SignUpNetSource
import com.coursework.speakoutchat.auth_domain_data.user_info.UserInfoLocalSource
import com.coursework.speakoutchat.common.extension.flatMap
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val signUpNetSource: SignUpNetSource,
    private val loginNetSource: LoginNetSource,
    private val userInfoLocalSource: UserInfoLocalSource
) : UserInfoProvider {

    override val userInfoFlow: Flow<UserInfo?> = userInfoLocalSource.userInfoFlow

    override suspend fun getUserInfo(): Result<UserInfo> = userInfoLocalSource.getUserInfo()

    suspend fun signUp(userCredentials: UserCredentials): Result<Unit> {
        return signUpNetSource.signUp(userCredentials)
    }

    suspend fun login(authRequest: AuthRequest): Result<Unit> {
        return loginNetSource.authenticate(authRequest).flatMap { token ->
            val userInfo = mapToUserInfo(authRequest, token)
            userInfoLocalSource.upsertUserInfo(userInfo)
        }
    }

    suspend fun checkUserInfoExists(): Result<Boolean> {
        return userInfoLocalSource.checkUserInfoExists()
    }

    suspend fun deleteUserInfo(): Result<Unit> {
        return userInfoLocalSource.deleteUserInfo()
    }

}