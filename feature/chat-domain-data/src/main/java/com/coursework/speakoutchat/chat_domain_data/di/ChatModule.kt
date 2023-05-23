package com.coursework.speakoutchat.chat_domain_data.di

import com.coursework.speakoutchat.network.type.ChatStompClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ChatModule {

    @Singleton
    @Provides
    @ChatStompClient
    fun provideChatStompClient(): StompClient = Stomp.over(
        Stomp.ConnectionProvider.OKHTTP,
        "ws://10.0.2.2:9899/chat-endpoint/websocket",
    )

}