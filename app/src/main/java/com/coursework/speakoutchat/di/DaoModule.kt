package com.coursework.speakoutchat.di

import com.coursework.speakoutchat.auth_domain_data.user_info.UserInfoDao
import com.coursework.speakoutchat.chat_domain_data.local.MessageDao
import com.coursework.speakoutchat.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {

    @Provides
    fun provideUserInfoDao(appDatabase: AppDatabase): UserInfoDao = appDatabase.userInfoDao()

    @Provides
    fun provideMessageDao(appDatabase: AppDatabase): MessageDao = appDatabase.messageDao()

}