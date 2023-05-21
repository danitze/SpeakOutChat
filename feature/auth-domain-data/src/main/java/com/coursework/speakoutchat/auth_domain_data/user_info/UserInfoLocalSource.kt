package com.coursework.speakoutchat.auth_domain_data.user_info

import com.coursework.speakoutchat.auth_domain_data.data.UserInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserInfoLocalSource @Inject constructor(
    private val userInfoDao: UserInfoDao
) {

    val userInfoFlow: Flow<UserInfo?> = userInfoDao.observeUserInfo().map { userInfoList ->
        require(userInfoList.size < 2) { "There can be only 1 user" }
        userInfoList.getOrNull(0)
    }

    suspend fun getUserInfo(): Result<UserInfo> = kotlin.runCatching {
        userInfoDao.getUserInfo()
    }

    suspend fun upsertUserInfo(userInfo: UserInfo): Result<Unit> = kotlin.runCatching {
        userInfoDao.upsertUserInfo(userInfo)
    }

    suspend fun deleteUserInfo(): Result<Unit> = kotlin.runCatching {
        userInfoDao.deleteUserInfo()
    }

    suspend fun checkUserInfoExists(): Result<Boolean> = kotlin.runCatching {
        userInfoDao.checkUserInfoExists()
    }

}