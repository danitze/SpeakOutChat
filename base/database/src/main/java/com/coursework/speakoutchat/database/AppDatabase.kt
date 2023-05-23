package com.coursework.speakoutchat.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.coursework.speakoutchat.auth_domain_data.data.UserInfo
import com.coursework.speakoutchat.auth_domain_data.user_info.UserInfoDao
import com.coursework.speakoutchat.chat_domain_data.data.Message
import com.coursework.speakoutchat.chat_domain_data.local.MessageDao

@Database(
    entities = [UserInfo::class, Message::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userInfoDao(): UserInfoDao

    abstract fun messageDao(): MessageDao

}