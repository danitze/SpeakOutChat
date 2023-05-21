package com.coursework.speakoutchat.auth_domain_data.user_info

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.coursework.speakoutchat.auth_domain_data.data.UserInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface UserInfoDao {

    @Query("SELECT * FROM user_info")
    fun observeUserInfo(): Flow<List<UserInfo>>

    @Upsert
    suspend fun upsertUserInfo(userInfo: UserInfo)

    @Query("DELETE FROM user_info")
    suspend fun deleteUserInfo()

    @Query("SELECT EXISTS (SELECT * FROM user_info)")
    suspend fun checkUserInfoExists(): Boolean

}