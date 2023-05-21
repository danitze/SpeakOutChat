package com.coursework.speakoutchat.partner_search_domain_data.di

import com.coursework.speakoutchat.network.type.PairingStompClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PartnerSearchModule {

    @Singleton
    @Provides
    @PairingStompClient
    fun providePairingStompClient(): StompClient = Stomp.over(
        Stomp.ConnectionProvider.OKHTTP,
        "ws://10.0.2.2:9900/pairing-endpoint/websocket",
    )

}