package com.coursework.speakoutchat.network.di

import com.coursework.speakoutchat.network.interceptor.BearerTokenInterceptor
import com.coursework.speakoutchat.network.type.TokenInterceptor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingModule {

    @Binds
    @TokenInterceptor
    abstract fun bindBearerTokenInterceptor(
        bearerTokenInterceptor: BearerTokenInterceptor
    ): Interceptor

}