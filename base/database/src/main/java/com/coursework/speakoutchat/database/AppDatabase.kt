package com.coursework.speakoutchat.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.coursework.speakoutchat.auth_domain_data.data.UserInfo
import com.coursework.speakoutchat.auth_domain_data.user_info.UserInfoDao

@Database(
    entities = [UserInfo::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userInfoDao(): UserInfoDao

}