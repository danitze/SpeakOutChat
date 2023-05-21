package com.coursework.speakoutchat.auth_domain_data.di

import com.coursework.speakoutchat.auth_domain_data.provider.UserInfoProvider
import com.coursework.speakoutchat.auth_domain_data.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthBindingModule {

    @Binds
    abstract fun bindUserInfoProvider(
        authRepository: AuthRepository
    ): UserInfoProvider

}