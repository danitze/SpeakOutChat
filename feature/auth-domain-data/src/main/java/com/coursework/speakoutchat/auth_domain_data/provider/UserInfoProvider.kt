package com.coursework.speakoutchat.auth_domain_data.provider

import com.coursework.speakoutchat.auth_domain_data.data.UserInfo
import kotlinx.coroutines.flow.Flow

interface UserInfoProvider {

    val userInfoFlow: Flow<UserInfo?>

    suspend fun getUserInfo(): Result<UserInfo>

}