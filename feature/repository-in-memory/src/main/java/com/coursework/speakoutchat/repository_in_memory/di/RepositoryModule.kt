package com.coursework.speakoutchat.repository_in_memory.di

import com.coursework.speakoutchat.chat_ui.NavigateToMenuEventApi
import com.coursework.speakoutchat.menu_ui.menu.NavigateToLoginEventApi
import com.coursework.speakoutchat.menu_ui.topic_choose.TopicChosenEventApi
import com.coursework.speakoutchat.repository_in_memory.impl.NavigateToLoginEventImpl
import com.coursework.speakoutchat.repository_in_memory.impl.NavigateToMenuEventImpl
import com.coursework.speakoutchat.repository_in_memory.impl.TopicChosenEventImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindTopicChosenEventApi(impl: TopicChosenEventImpl): TopicChosenEventApi

    @Binds
    abstract fun bindNavigateToLoginEventApi(impl: NavigateToLoginEventImpl): NavigateToLoginEventApi

    @Binds
    abstract fun bindNavigateToMenuEventApi(impl: NavigateToMenuEventImpl): NavigateToMenuEventApi
}