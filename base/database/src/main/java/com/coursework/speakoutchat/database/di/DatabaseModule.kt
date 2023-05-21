package com.coursework.speakoutchat.database.di

import android.content.Context
import androidx.room.Room
import com.coursework.speakoutchat.database.APP_DATABASE_NAME
import com.coursework.speakoutchat.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase = Room
        .databaseBuilder(context, AppDatabase::class.java, APP_DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()

}